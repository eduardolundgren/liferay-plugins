Liferay.Service.register("Liferay.Service.Newsletter", "com.liferay.newsletter.service", "newsletter-portlet");

Liferay.Service.registerClass(
	Liferay.Service.Newsletter, "NewsletterCampaign",
	{
		addCampaign: true,
		deleteCampaign: true,
		sendCampaign: true,
		resendCampaignToFailedContacts: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Newsletter, "NewsletterContent",
	{
		addContent: true,
		deleteContent: true,
		updateContent: true
	}
);