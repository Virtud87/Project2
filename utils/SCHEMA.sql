drop table if exists business, shark;

create table business (
	businessId serial primary key,
	firstName varchar(20) not null,
	lastName varchar(20) not null,
	businessName varchar(20) not null,
	username varchar(20) not null,
	password varchar(10) not null
);

create table shark (
	sharkId serial primary key,
	firstName varchar(20) not null,
	lastName varchar(20) not null,
	businessName varchar(30) not null,
	username varchar(20) not null,
	password varchar(20) not null
);

create table pitches (
	pitchId serial primary key, 
	businessId int references business(businessId) on delete cascade,
	creationDate varchar(10) not null,
	pitch text not null, 
	amount decimal not null, 
	percentage decimal not null
);

create table commenting (
	commentId serial primary key,
	businessId int references business(businessId) on delete cascade,
	pitchId int references pitches(pitchId) on delete cascade,
	commenting text,
	createDate varchar(10) not null
);