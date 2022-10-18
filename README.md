# azure-function-env-benchmark
A benchmark that measures the performance of reading environment variables in an Azure Function 

This benchmark measures the performance of reading environment variables in an Azure Function. The test is performed by reading a variable from the Azure Function environment, using the System.getProperty method, and then measuring the time it takes to read the variable.


## How to run
```
export VARIABLE_EXISTS=true && mvn package
```
## How to deploy
```
export VARIABLE_EXISTS=true && mvn package && mvn azure-functions:deploy
```

## Output
```
Performance Test for VARIABLE_EXISTS
Duration: 265ms 
Performance Test for VARIABLE_NOT_EXISTING
Duration: 266ms 
```
