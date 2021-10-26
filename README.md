# rpmeta
RP Meta


Problema ao mandar email, quando o debug estiver ativado dá o erro:
Error: javax.net.ssl.SSLHandshakeException: No appropriate protocol
(protocol is disabled or cipher suites are inappropriate)

Solution:

Method A
Modifying the file java.security inside the JDK install directory:

Edit the file $JAVA_HOME/conf/security/java.security in a text editor. 
Remove the entries TLSv1, and TLSv1.1, from the following line of that file:
jdk.tls.disabledAlgorithms=SSLv3, TLSv1, TLSv1.1, RC4, DES,
Restart the application

https://support.azul.com/hc/en-us/articles/360061143191-TLSv1-v1-1-No-longer-works-after-upgrade-No-appropriate-protocol-error
