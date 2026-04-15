package com.sagarannaldas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoDBConfig {

	@Value("${aws.endpoint}")
	private String dynamodbEndpoint;

	@Value("${aws.dynamodb.region}")
	private String awsRegion;

	@Value("${aws.credentials.access-key}")
	private String dynamodbAccessKey;

	@Value("${aws.credentials.secret-key}")
	private String dynamodbSecretKey;

	@Value("${aws.dynamo.sessionToken}")
	private String dynamodbSessionToken;

	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		return new DynamoDBMapper(buildAmazonDynamoDB());
	}

	private AmazonDynamoDB buildAmazonDynamoDB() {
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(dynamodbEndpoint, awsRegion))
				.withCredentials(
						new AWSStaticCredentialsProvider(new BasicAWSCredentials(dynamodbAccessKey, dynamodbSecretKey)))
				.build();
	}

//	public DynamoDBMapper mapper() {
//		return new DynamoDBMapper(amazonDynamoDBConfig(), null);
//	}
//
//	private AmazonDynamoDB amazonDynamoDBConfig() {
//		return AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
//				new AwsClientBuilder.EndpointConfiguration("dynamodb.us-east-1.amazonaws.com", "us-east-1")
//						.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(
//								"AKIA2U6V2J6BSROPKLPU", "Sgkv9mrsv/2WjiW2s8QCjSiFDMJ9H/9UrwkPEkRe")))
//						.build());
//	}

}

