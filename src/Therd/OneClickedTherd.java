package Therd;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

import com.qq.server.dao.FindUserInfo;
import com.qq.server.dao.GetAllFriend;
import com.qq.server.dao.GropManage;
import com.qq.server.dao.UpdateFriend;

import Message.Message;
import Message.UserBean;

public class OneClickedTherd extends Thread{
	Socket s;

	public OneClickedTherd(Socket s) {
		// �ѷ������͸ÿͻ��˵����Ӹ���s
		this.s = s;
	}


	public void run() {

		while (!isInterrupted()) {

			// ������߳̾Ϳ��Խ��տͻ��˵���Ϣ.
			try {
				ObjectInputStream ois = new ObjectInputStream(s
						.getInputStream());
				Message m = (Message) ois.readObject();

				 //System.out.println(m.getSender()+" �� "+m.getGetter()+" ˵:"+m.getCon());
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				// �Դӿͻ���ȡ�õ���Ϣ���������жϣ�Ȼ������Ӧ�Ĵ���
				if (m.getTcp_ip() == 15) {
					// һ�����ת��.
					// ȡ�ý����˵�ͨ���߳�
				}

			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

		}

	}

}
