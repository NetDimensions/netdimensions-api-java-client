# Talent Suite Batch Report Downloader

Simple command-line application that downloads a user's batch reports to a local directory.

## Usage

The Batch Report Downloader is invoked as shown below. The optional `<limit>` argument specifies the maximum number of reports to download, which will be the most recent. 

```shell
java -jar batch-report-downloader-1.1-jar-with-dependencies.jar <Talent Suite base URL> <user ID> <password> <target directory> <limit>
```

For example, the command below downloads at most the three most recent reports for user **johndoe** from the site at `https://www.example.com/ekp/`.

```shell
java -jar batch-report-downloader-1.1-jar-with-dependencies.jar https://www.example.com/ekp/ johndoe mypassword . 3
```
