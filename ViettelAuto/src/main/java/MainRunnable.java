import com.vt.ViettelAutoProcessor;
import com.vt.dto.LoginDTO;
import com.vt.dto.LoginMethodEnum;
import com.vt.dto.NapTheDTO;
import com.vt.dto.NapTheMethodEnum;

public class MainRunnable extends Thread {
	private final static String PATH_TO_EXE_SELENIUM = "C:\\Users\\Longluffy\\Desktop\\tool\\phantomjs\\phantomjs.exe";

	public static void main(String[] args) {
		Thread threadKeepSession = new Thread() {
			public void run() {
				LoginDTO loginDto = new LoginDTO("t008_gftth_linhnt228", "Yen@123456", LoginMethodEnum.ADSL_FTTH_NEXTTV);
				NapTheDTO naptheDto = new NapTheDTO(NapTheMethodEnum.DIDONG_DCOM, "0961005566", "117892656195269");

				for (int i = 0; i < 1; i++) {
					System.out.println("Chay lan : " + i);
					try {
						Thread.sleep(1000);
						ViettelAutoProcessor viettelAuto = new ViettelAutoProcessor(loginDto, naptheDto,PATH_TO_EXE_SELENIUM);
						viettelAuto.execute();
					} catch (InterruptedException e) { 
						e.printStackTrace();
					}

				}
			}
		};
		threadKeepSession.start();
	}

}
