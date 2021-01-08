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

## Installation
This API is not available yet from the community applications plugin. It will need to be installed manually.

1. Under the docker tab click `Add Container`
2. Give the application a name, I call it `unREST`
3. Repository: `savadev/unrest:6.8.3-latest`
4. Network Type: `bridge`
5. Add the following volumes by clicking `Add another Path, Port, Variable, Label or Device`

| Type | Name | Container Path | Host Path | Access Mode |
| --- | --- | --- | --- | --- |
| Path | State | /app/config/state/ | /var/local/emhttp/ | Read Only |
| Path | Application Configuration | /app/config/ | /mnt/user/appdata/unrest | Read/Write |
| Path | Users | /app/config/passwd | /etc/passwd | Read Only |
| Path | Groups | /app/config/group | /etc/group | Read Only |
| Path | Shadow | /app/config/shadow | /etc/shadow | Read Only |
| Path | EmHTTPD Socket | /app/config/emhttpd.socket | /var/run/emhttpd.socket | Read/Write |
| Path | Docker Socket | /app/config/docker.sock | /var/run/docker.sock | Read/Write |

6. Add the following ports by clicking `Add another Path, Port, Variable, Label or Device`

| Type | Name | Container Port | Host Port | Connection Type |
| --- | --- | --- | --- | --- |
| Port | API Port | 8080 | Whichever Port You Want | TCP |

7. Click `Apply`

#API Operations
##Security
All APIs are protected using the same authentication mechanisms as Unraid itself. APIs can be invoked by any user with the proper permissions. For example,
calling a Docker API with a user that is not in the docker group will result in an authorization error.

There are two ways to successfully authenticate an API call.
###Basic Authentication
Basic authentication works by Base64 encoding your username and password and sending them in the `Authorization: Basic {credentials}` header of the request.

###Bearer Authorization (JWT)
Bearer authorization works by generating a cryptographically secure token which can then be used to make API calls on behalf of the user who generated the token.
These tokens have the same permissions as the user who generated them.
The bearer token should be included in the `Authorization: Bearer {token}` header of the request.
Tokens can be generated using the Security API.

## Security API
##### Generate Token
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X GET http://unraid:port/api/security/jwt`

##### Rotate Keys
In the event of a security breach, the root user is authorized to rotate the keys used to generate tokens.
Upon rotation, all tokens generated prior to the rotation become invalid.

`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/security/jwk/rotate`

## Disk API

##### Get All Disks
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X GET http://unraid:port/api/disks`

##### Get All Cache Disks
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X GET http://unraid:port/api/disks/cache`

##### Get All Parity Disks
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X GET http://unraid:port/api/disks/parity`

##### Get All Data Disks
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X GET http://unraid:port/api/disks/data`

##### Get All Flash Disks
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X GET http://unraid:port/api/disks/flash`

##### All Disks Up
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/disks/up`

##### All Parity Disks Up
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/disks/parity/up`

##### All Cache Disks Up
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/disks/cache/up`

##### All Data Disks Up
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/disks/data/up`

##### All Disks Down
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/disks/down`

##### All Parity Disks Down
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/disks/parity/down`

##### All Cache Disks Down
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/disks/cache/down`

##### All Data Disks Down
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/disks/data/down`

##### Spin Up By IDX
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/disk/{IDX}/down`

##### Spin Down By IDX
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/disk/{IDX}/up`

## Configuration API
##### Get Unraid Configuration Variables
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X GET http://unraid:port/api/config`

## Docker API

**Note 1:** All docker APIs require a user with `docker` permissions (user belongs to the docker unix group).

**Note 2:** The following APIs do not have any effect on the unREST API container in order to prevent lock out via the API.

##### Get All Containers
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X GET http://unraid:port/api/docker/containers`

##### Get Container by ID
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X GET http://unraid:port/api/docker/container/{id}`

##### Start All Containers
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/docker/containers/start`

##### Start Container by ID
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/docker/container/{id}/start`

##### Stop All Containers
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/docker/containers/stop`

##### Stop Container by ID
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/docker/container/{id}/stop`

##### Restart All Containers
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/docker/containers/restart`

##### Restart Container by ID
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/docker/container/{id}/restart`

##### Pause All Containers
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/docker/containers/pause`

##### Pause Container by ID
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/docker/container/{id}/pause`

##### Unpause All Containers
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/docker/containers/unpause`

##### Unpause Container by ID
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/docker/container/{id}/unpause`

##### Kill All Containers
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/docker/containers/kill`

##### Kill Container by ID
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/docker/container/{id}/kill`

## Share API
##### Get All Shares
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X GET http://unraid:port/api/shares`