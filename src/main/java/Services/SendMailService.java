package Services;

import Model.EmailMaster;

public interface SendMailService {
	
	public boolean sendEmail(EmailMaster mail);
}
