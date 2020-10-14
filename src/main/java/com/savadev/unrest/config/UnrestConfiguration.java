package com.savadev.unrest.config;

import com.savadev.unrest.config.properties.DiskOperationsProperties;
import com.savadev.unrest.config.properties.DockerOperationsProperties;
import com.savadev.unrest.config.properties.OperationsProperties;
import com.savadev.unrest.config.properties.StateProperties;
import com.savadev.unrest.config.properties.UserProperties;
import com.savadev.unrest.dao.client.disk.DiskOperations;
import com.savadev.unrest.dao.client.disk.DiskOperationsClient;
import com.savadev.unrest.dao.client.docker.DockerOperations;
import com.savadev.unrest.dao.client.docker.DockerOperationsClient;
import com.savadev.unrest.dao.file.FileResourceLoader;
import com.savadev.unrest.dao.file.GroupFileRepository;
import com.savadev.unrest.dao.file.GroupRepository;
import com.savadev.unrest.dao.file.ShadowFileRepository;
import com.savadev.unrest.dao.file.ShadowRepository;
import com.savadev.unrest.dao.file.UserFileRepository;
import com.savadev.unrest.dao.file.UserRepository;
import com.savadev.unrest.dao.ini.DiskIniRepository;
import com.savadev.unrest.dao.ini.DiskRepository;
import com.savadev.unrest.dao.ini.ShareIniRepository;
import com.savadev.unrest.dao.ini.ShareRepository;
import com.savadev.unrest.dao.ini.StateResourceLoader;
import com.savadev.unrest.dao.ini.VarIniRepository;
import com.savadev.unrest.dao.ini.VarRepository;
import com.savadev.unrest.service.disk.DiskService;
import com.savadev.unrest.service.disk.UnrestDiskService;
import com.savadev.unrest.service.docker.DockerService;
import com.savadev.unrest.service.docker.UnrestDockerService;
import com.savadev.unrest.service.share.ShareService;
import com.savadev.unrest.service.share.UnrestShareService;
import com.savadev.unrest.service.user.UnrestUserService;
import com.savadev.unrest.service.user.UserService;
import io.netty.channel.unix.DomainSocketAddress;
import org.ini4j.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.concurrent.Executors;

@Configuration
public class UnrestConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "application.state")
    StateProperties stateProperties() {
        return new StateProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "application.operations.disk")
    DiskOperationsProperties diskOperationsProperties() {
        return new DiskOperationsProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "application.operations.docker")
    DockerOperationsProperties dockerOperationsProperties() {
        return new DockerOperationsProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "application.user")
    UserProperties userProperties() {
        return new UserProperties();
    }

    @Bean
    StateResourceLoader resourceLoader() {
        return new StateResourceLoader(stateProperties(), Executors.newCachedThreadPool());
    }

    @Bean
    FileResourceLoader fileResourceLoader() {
        return new FileResourceLoader();
    }

    @Bean
    WebClient diskOperationsClient() {
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(getOperationsClient(diskOperationsProperties())))
                .build();
    }

    @Bean
    WebClient dockerOperationsClient() {
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(getOperationsClient(dockerOperationsProperties())))
                .build();
    }

    private HttpClient getOperationsClient(OperationsProperties properties) {
        var socket = properties.getSocket();
        return HttpClient.create()
                .remoteAddress(() -> isRemoteAddress(socket) ?
                        new InetSocketAddress(socket.getHost(), socket.getPort()) :
                        new DomainSocketAddress(socket.toString()));
    }

    private boolean isRemoteAddress(URI socket) {
        return switch (socket.getScheme()) {
            case "http", "https" -> true;
            default -> false;
        };
    }

    @Bean
    DiskOperations diskOperations() {
        return new DiskOperationsClient(diskOperationsProperties(), diskOperationsClient(), varRepository());
    }

    @Bean
    DockerOperations dockerOperations() {
        return new DockerOperationsClient(dockerOperationsProperties(), dockerOperationsClient());
    }

    @Bean
    DiskRepository diskRepository() {
        return new DiskIniRepository(resourceLoader());
    }

    @Bean
    VarRepository varRepository() {
        var config = new Config();
        config.setGlobalSection(true);
        return new VarIniRepository(config, resourceLoader());
    }

    @Bean
    ShareRepository shareRepository() {
        return new ShareIniRepository(resourceLoader());
    }

    @Bean
    UserRepository userRepository() {
        return new UserFileRepository(userProperties(), fileResourceLoader());
    }

    @Bean
    ShadowRepository shadowRepository() {
        return new ShadowFileRepository(userProperties(), fileResourceLoader());
    }

    @Bean
    GroupRepository groupRepository() {
        return new GroupFileRepository(userProperties(), fileResourceLoader());
    }

    @Bean
    DiskService deviceService() {
        return new UnrestDiskService(diskRepository(), diskOperations());
    }

    @Bean
    ShareService shareService() {
        return new UnrestShareService(shareRepository());
    }

    @Bean
    DockerService dockerService() {
        return new UnrestDockerService(dockerOperations());
    }

    @Bean
    UserService userService() {
        return new UnrestUserService(userRepository(), groupRepository(), shadowRepository());
    }

}
