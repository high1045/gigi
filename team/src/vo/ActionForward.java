package vo;

public class ActionForward {

	private String path; //포워딩할 View 페이지 URL 을 저장
	private boolean Redirect; //포워딩 방식 저장
	//true : Redirect 방식, fase : Dispatcher 방식
	
	//Getter/Setter 정의
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return Redirect;
	}
	public void setRedirect(boolean redirect) {
		Redirect = redirect;
	}
	
}
