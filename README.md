# Development

###Add the following Unraid paths to /etc/exports.
- `"/var/local/emhttp" -async,no_subtree_check,fsid=100 *(sec=sys,ro,insecure,anongid=100,anonuid=99,all_squash)`
- `"/etc" -async,no_subtree_check,fsid=100 *(sec=sys,ro,insecure,anongid=100,anonuid=99,all_squash)`

Then run `exportfs -a`

###Mount the exports to your development machine
- `sudo mount -t nfs SERVER:/var/local/emhttp/ /mnt/unrest/state/`
- `sudo mount -t nfs SERVER:/etc/passwd /mnt/unrest/etc/passwd`
- `sudo mount -t nfs SERVER:/etc/group /mnt/unrest/etc/group`
- `sudo mount -t nfs SERVER:/etc/shadow /mnt/unrest/etc/shadow` 

###Expose Unix Domain Sockets over TCP
- `nohup socat TCP-LISTEN:12345,fork UNIX-CONNECT:/var/run/emhttpd.socket &`
- `nohup socat TCP-LISTEN:12346,fork UNIX-CONNECT:/var/run/docker.sock &`
