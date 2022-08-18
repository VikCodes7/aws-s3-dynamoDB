## Code Steps
### S3 Buckets Creation and Insertion of file.
Main code is present in CreateS3Bucket.java

File Path: src/main/java/com/assignment/

#### Running S3 Bucket Code
** start the AWS instance and keep access key, secret key, and session key in credential files as per location below

Windows: C:\Users\<yourUserName>\.aws\credentials
MAC: ~/.aws/credentials

Then run the main function and then to verify check aws cli.

### DynamoDB Update and item insertion in collection.
Main code is present in DynamoDBService.java

File Path: src/main/java/com/assignment/

#### Running DynamoDB Code
** start the AWS instance and keep access key, secret key, and session key in credential files as per location below

** Then create a collection called Park_Novascotia and then run the code.

** First the item will be inserted and then it will be updated with new column called "type_of_campsite".