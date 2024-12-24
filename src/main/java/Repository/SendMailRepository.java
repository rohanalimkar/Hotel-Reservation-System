package Repository;

import Model.EmailMaster;

public interface SendMailRepository {

	public boolean sendEmail(EmailMaster mail);
}
