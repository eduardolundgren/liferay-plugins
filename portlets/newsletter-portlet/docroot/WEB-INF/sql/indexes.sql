create index IX_58F76CCF on Newsletter_NewsletterCampaign (contentId);
create index IX_7E53C70B on Newsletter_NewsletterCampaign (sendDate, sent);
create index IX_A93605DF on Newsletter_NewsletterCampaign (uuid_);
create unique index IX_313E99AB on Newsletter_NewsletterCampaign (uuid_, groupId);

create index IX_C17426C5 on Newsletter_NewsletterContact (email);

create index IX_A28728F4 on Newsletter_NewsletterContent (uuid_);
create unique index IX_378B1E36 on Newsletter_NewsletterContent (uuid_, groupId);

create index IX_4DCAD2CA on Newsletter_NewsletterLog (campaignId);
create index IX_DA01CD41 on Newsletter_NewsletterLog (campaignId, contactId);
create index IX_55EBA8D6 on Newsletter_NewsletterLog (campaignId, sent);
create index IX_E3901C20 on Newsletter_NewsletterLog (contactId);