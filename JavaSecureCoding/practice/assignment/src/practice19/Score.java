// ��ǻ���а� 20170964 �̿���
package practice19;

public enum Score {
	WIN, LOSE, EQUAL;

	public static String print(Score rslt) {
		switch (rslt.name()) {
			case "WIN":
				return "����� �̰���ϴ�.";
			case "LOSE":
				return "��ǻ�Ͱ� �̰���ϴ�.";
			case "EQUAL":
				return "�����ϴ�.";
			default:
				return null;
		}
	}
}