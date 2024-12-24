package Services;

import Model.EmailMaster;
import Repository.SendMailRepository;
import Repository.SendMailRepositoryImp;

public class SendMailServiceImp implements SendMailService , SendMailRepository {

	SendMailRepository mailRepo=new SendMailRepositoryImp();
	
	@Override
	public boolean sendEmail(EmailMaster mail) {
		// TODO Auto-generated method stub
		return mailRepo.sendEmail(mail);
	}

}
