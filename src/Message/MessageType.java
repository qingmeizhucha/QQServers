/*
 * ����Ϊͨ��Э����
 */
package Message;


public class MessageType {
	private int user_out=0;//�û��˳�
	private int user_login=1;//�û���¼
	private int user_reg=2;  //�û�ע��
	private int reg_suc=3;//�û�ע��ɹ�
	private int user_log_suc=4;//��¼�ɹ�
	private int user_log_filed=5;//��¼ʧ��
	private int get_user_friend=6;//��ȡ���еĺ������
	private int get_user_grop=7;//��ȡ���еķ���
	private int get_user_info=8;//��ȡĳ��ID��������Ϣ
	private int update_friend_beizhu=9;//�޸ĺ��ѱ�ע
	private int del_friend=10;//ɾ������
	private int move_friend=11;//�ƶ����ѷ���
	private int add_newgrop=12;//����µķ���
	private int update_gropname=13;//�޸ķ�����
	private int del_grop=14;//ɾ������
	private int sendmessage=15;//������Ϣ
	private int add_friend=16;//��Ӻ���
}
