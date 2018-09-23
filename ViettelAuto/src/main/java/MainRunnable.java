import com.vt.ViettelAutoProcessor;
import com.vt.dto.LoginDTO;
import com.vt.dto.LoginMethodEnum;
import com.vt.dto.NapTheDTO;
import com.vt.dto.NapTheMethodEnum;

public class MainRunnable {
	private final static String PATH_TO_EXE_SELENIUM = "C:\\Tool\\phantomjs\\phantomjs.exe";

	public static void main(String[] args) {

		LoginDTO loginDto = new LoginDTO("h004_gftth_longnd193", "020791a@", LoginMethodEnum.ADSL_FTTH_NEXTTV);
		NapTheDTO naptheDto = new NapTheDTO(NapTheMethodEnum.DIDONG_DCOM, "0961005566", "511380099753328");

		ViettelAutoProcessor viettelAuto = new ViettelAutoProcessor(loginDto, naptheDto, PATH_TO_EXE_SELENIUM);
		viettelAuto.execute();
	}
}
