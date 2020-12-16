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
| Port | App Port | 8080 | Whichever Port You Want | TCP |

7. Click `Apply`

Examples

##### All Disks Up
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/disks/up`

##### All Disks Down
`curl -i --user root:Password -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://unraid:port/api/disks/down`