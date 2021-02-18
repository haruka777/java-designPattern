import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 */

/**
 * @author haruka777
 *
 * ブラックジャック（トランプのゲーム）のコードです.
 *
 * 【仕様】
 * ①初期カードは52枚。引く際にカードの重複は無いようにする.
 * ②プレイヤーとディーラーの2人対戦。プレイヤーは実行者、ディーラーは自動的に実行.
 * ③実行開始時、プレイヤーとディーラーは順番にカードを1枚引く。引いたカードは画面に表示する.
 * ④ディーラーのカードは2枚目以降表示しない.
 * ⑤プレイヤーは、カードを引くたびに、次のカードを引くか選択できる.
 * ⑥プレイヤーが21を超えていたらバースト、その時点でゲームに敗北する.
 * ⑦ディーラーは手札の合計が「17」以上になるまで山札を引き続ける.
 * ⑧ディーラーの手札の合計が「21」を超えた場合、バーストしてプレイヤーの勝利.
 * ⑨ディーラーの手札が「18」以上「21」以下になったとき、プレイヤーとディーラーの手札の合計を比較し、大きい方が勝利.
 * ⑩JとQとKは「10」として扱う。Aは「1」としてだけ扱う.
 * ⑪ダブルダウンなし、スプリットなし、サレンダーなし、その他特殊そうなルールなし.
 *
 */
public class BlackJack {

	/**
	 * ブラックジャックのメイン処理.
	 */
	public static void main(String[] args) {

		// シャッフルされた山札を作る
		List<Integer> deck = new ArrayList<>(52);
		shuffleDeck(deck);

		// プレイヤー・ディーラーの手札リストを定義
		List<Integer> player = new ArrayList<>();
		List<Integer> dealer = new ArrayList<>();

		// それぞれ1枚ずつカードを山札から引く
		player.add(deck.get(0));
		dealer.add(deck.get(1));

		// 山札とプレイヤー手札の進行状況を記録する変数を用意する
		int deckCount = 2;
		int playerHands = 1;

		// それぞれの手札のポイントを表示
		System.out.println("あなたの1枚目のカードは" + toDescription(player.get(0)));
		System.out.println("ディーラーの1枚目のカードは" + toDescription(dealer.get(0)));
		System.out.println("あなたの2枚めのカードは" + toDescription(player.get(1)));
		System.out.println("ディーラーの2枚めのカードは秘密です。");

		//プレイヤー・ディーラーのポイントを集計
		int playerPoint = sumPoint(player);
		int dealerPoint = sumPoint(dealer);
		System.out.println("あなたの現在のポイントは" + playerPoint + "です。");

		// プレイヤーがカードを引くか、引くのを辞めるかループ
		while (true) {
			System.out.println("カードを引きますか？Yes:y or No:n");

			//キーボードの入力を受け付けて、変数strに代入する
			Scanner scan = new Scanner(System.in);
			String str = scan.next();

			if ("n".equals(str)) {
				break;
			} else if ("y".equals(str)) {
				//手札に山札から1枚加える
				player.add(deck.get(deckCount));
				//山札と手札を一枚進める
				deckCount++;
				playerHands++;

				System.out.println("あなたの" + playerHands + "枚目のカードは" + toDescription(player.get(playerHands - 1)));
				playerPoint = sumPoint(player);
				System.out.println("現在の合計は" + playerPoint);

				//プレイヤーのバーストチェック
				if (isBusted(playerPoint)) {
					System.out.println("残念、バーストしてしまいました。");
					return;
				}

			} else {
				System.out.println("あなたの入力は" + str + " です。y か n を入力してください。");
			}
		}

		// ディーラーが手札のポイントが17以上になるまでカードを引き続ける
		while (true) {
			//手札が17以上の場合、ブレークする
			if (dealerPoint >= 17) {
				break;
			} else {
				//手札に山札から1枚加える
				dealer.add(deck.get(deckCount));
				//山札を一枚進める
				deckCount++;

				//ディーラーの合計ポイントを計算
				dealerPoint = sumPoint(dealer);
				//ディーラーのバーストチェック
				if (isBusted(dealerPoint)) {
					System.out.println("ディーラーがバーストしました。あなたの勝ちです！");
					return;
				}
			}
		}

		// それぞれのポイントを比較して、勝敗判定
		System.out.println("プレイヤーのポイントは" + playerPoint);
		System.out.println("ディーラーのポイントは"+ dealerPoint);

		if(playerPoint == dealerPoint) {
		    System.out.println("引き分け");
		} else if(playerPoint > dealerPoint) {
		    System.out.println("勝ち");
		} else {
		    System.out.println("負け");
		}
	}

	/**
	 * 山札をシャッフルします.
	 *
	 * @param deck
	 */
	private static void shuffleDeck(List<Integer> deck) {
		// リストに1-52の連番を代入
		for (int i = 1; i <= 52; i++) {
			deck.add(i);
		}

		//山札をシャッフル
		Collections.shuffle(deck);
	}

	/**
	 * 山札の数をマークに置き換えます.
	 *
	 * @param deck 山札の数
	 * @return マーク
	 */
	private static String toSuit(int cardNumber) {
		switch ((cardNumber - 1) / 13) {
		case 0:
			return "スペード";
		case 1:
			return "ハート";
		case 2:
			return "クラブ";
		case 3:
			return "ダイヤ";
		default:
			return "例外";
		}
	}

	/**
	 * 山札の数をカードの数に置き換えます.
	 * @param cardNumber 値
	 * @return numberカードの数
	 */
	private static int toNumber(int cardNumber) {
		int number = cardNumber % 13;
		if (number == 0) {
			number = 13;
		}

		return number;
	}

	/**
	 * カード番号を値（AやJ,Q,K）に変換します.
	 * @param number カード番号
	 * @return str 値
	 */
	//
	private static String toRank(int number) {
		switch (number) {
		case 1:
			return "A";
		case 11:
			return "J";
		case 12:
			return "Q";
		case 13:
			return "K";
		default:
			String str = String.valueOf(number);
			return str;
		}
	}

	/**
	 * 山札の数を「マーク」の「値」の文字列に置き換えます.
	 *
	 * @param cardNumber
	 * @return 「マーク」の「値」
	 */
	private static String toDescription(int cardNumber) {
		String rank = toRank(toNumber(cardNumber));
		String mark = toSuit(cardNumber);

		return mark + "の" + rank;
	}

	/**
	 * トランプの数字を得点計算用のポイントに変換します.
	 * J/Q/Kは10とします.
	 * @param num ポイント
	 * @return num ポイント
	 */
	private static int toPoint(int num) {
		if (num == 11 || num == 12 || num == 13) {
			num = 10;
		}

		return num;
	}

	/**
	 * 現在の合計ポイントを計算します.
	 * @param list 手札
	 * @return sum 合計ポイント
	 */
	private static int sumPoint(List<Integer> list) {
		int sum = 0;

		for (int i = 0; i < list.size(); i++) {
			sum = sum + toPoint(toNumber(list.get(i)));
		}

		return sum;
	}

	/**
	 * 手札がバーストしているか判定します.
	 * @param point 手札の合計ポイント
	 * @return バースト判定結果
	 */
	private static boolean isBusted(int point) {
		if (point <= 21) {
			return false;
		} else {
			return true;
		}
	}

}
