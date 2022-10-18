# azure-function-env-benchmark
A benchmark that measures the performance of reading environment variables in an Azure Function 

This benchmark measures the performance of reading environment variables in an Azure Function. The test is performed by reading a variable from the Azure Function environment, using the System.getProperty method, and then measuring the time it takes to read the variable.


## How to run
```
export VARIABLE_EXISTS=true && mvn package
```
