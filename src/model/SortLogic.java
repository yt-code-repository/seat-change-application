package model;

import java.util.ArrayList;
import java.util.List;

public class SortLogic {
	/**
	 * Seat_id のみ、
	 * 並べ替えて、そのSeat_idをJavaBeansに設定する
	 *
	 * @param memberList : MemberBean の配列
	 * @param p		  : 前に要素が何個あるか
	 */
	public void execute(List<Member> memberList, int p) {

		int num= memberList.size();

		ArrayList< Integer > arr = new ArrayList<>();
		ArrayList< Integer > sorted_arr = new ArrayList<>();

		// 連番配列を用意
		for(int i=p; i<(p+num) ;i++) {
			arr.add(i);
		}

		// コンソール出力
//		System.out.println("");
//		System.out.println("arr.size() : " + arr.size());
//		System.out.println("");
//		System.out.println("arr.get(i)");
//		for(int i=0; i<num ;i++) {
//			System.out.println(arr.get(i));
//		}


		// 乱数を発生して、連番配列を並び替えて、インデックス配列を用意
		// 並べ替えあり
		for(int n=num; n>0; n--) {
			int index = (int) (Math.random() * n);
			sorted_arr.add(arr.get(index));
			arr.remove(index);
		}

		// 並べ替えなし
//		for(int i=p; i<(p+num) ;i++) {
//			sorted_arr.add(i);
//		}

		// コンソール出力
//		System.out.println("");
//		System.out.println("arr.size() : " + arr.size());
//		System.out.println("");
//		System.out.println("sorted_arr.get(i)");
//		for(int i=0; i<num ;i++) {
//			System.out.println(sorted_arr.get(i));
//		}

		// 要素のシートIDを設定
		for(int i=0; i<num; i++) {
			memberList.get(i).setSeat_id(sorted_arr.get(i) + 1);
		}
	}
}
