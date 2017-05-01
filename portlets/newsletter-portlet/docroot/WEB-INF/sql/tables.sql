create table Newsletter_NewsletterCampaign (
	uuid_ VARCHAR(75) null,
	campaignId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	contentId LONG,
	emailSubject VARCHAR(75) null,
	senderEmail VARCHAR(75) null,
	senderName VARCHAR(75) null,
	sent BOOLEAN,
	sendDate DATE null
);

create table Newsletter_NewsletterContact (
	contactId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	email VARCHAR(75) null,
	name VARCHAR(75) null
);

create table Newsletter_NewsletterContent (
	uuid_ VARCHAR(75) null,
	contentId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title VARCHAR(75) null,
	content VARCHAR(75) null,
	articleId LONG
);

create table Newsletter_NewsletterLog (
	logId LONG not null primary key,
	campaignId LONG,
	contactId LONG,
	sent BOOLEAN
);