# [savage-development/unREST](https://github.com/savage-development/unREST)

unREST is a reactive REST API for [Unraid](https://unraid.net/). It provides a fully authenticated integration point for extending the functionality of your Unraid Server.
Data fetched from multiple sources on your Unraid Server.

Disk Information/Operations is parsed from the Unraid State .ini files located at `/var/local/emhttp/` in combination with the emhttpd socket.

Docker Information/Operations are performed using the docker socket located at `/var/run/docker.sock`

Authentication is performed directly using the systems resources. These files should be mounted with read only access.
 * `/etc/passwd`
 * `/etc/group`
 * `/etc/shadow` 

## Functionality
| API | Operations |
| --- | --- |
| Config | Server Configuration |
| Disk | Disk Operations |
| Docker | Docker Operations |
| Security | API Security |
| Shares | Shares Operations |