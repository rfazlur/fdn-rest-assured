## Installation

Use the git clone command to install.

```bash
git clone foobar
```

Copy property file into secret folder

Run test using maven command

For staging environment
```bash
mvn test -Denv.USER=stag
```
For production environment
```bash
mvn test -Denv.USER=prod
```