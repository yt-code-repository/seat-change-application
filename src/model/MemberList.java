package model;

import java.util.List;

public class MemberList {
	private static List<Member> MemberList;

	public static List<Member> getMemberList() {
		return MemberList;
	}

	public static void setMemberList(List<Member> memberList) {
		MemberList = memberList;
	}

}
