
public class PokerNew {

	// 카드 생성함수 정의
	public static int[] genCard() {
		int[] card = new int[5];
		for (int i = 0; i < 5; i++) {
			while (true) {
				card[i] = (int) (Math.random() * 52);
				int check = 0;
				for (int j = 0; j < i; j++) {
					// 중복제거
					if (card[i] == card[j]) {
						check = 1;
						break;
					}
				}
				if (check == 0)
					break;
			}
		}
		return card;
	}

	public static void main(String args[]) {
		int gameCnt = 0; // 게임 실행 횟수
		int[] card = new int[5]; // 카드배열
		String[] shape = new String[10]; // 모양
		String[] strNumber = new String[5]; // 숫자

		int i, j;
		int count = 0, flush = 0, straight = 0;
		int[] gameResult = new int[10]; // 결과 카운트

		// 상황에따라 무한반복
		do {
			// 반복(세트)마다 초기화
			count = 0;
			flush = 0;
			straight = 0;

			System.out.println("Card");
			System.out.println("");

			// 5장 카드 생성
			int[] shpaeTemp = new int[5];
			int[] numTemp = new int[5];
			card = genCard();
			for (i = 0; i < 5; i++)
				System.out.print("     " + card[i]);
			System.out.println("");
			for (i = 0; i < 5; i++) {
				shpaeTemp[i] = card[i] / 13;
				numTemp[i] = card[i] % 13;
			}

			// spades, diamonds, hearts, clubs 및 카드정수값
			for (i = 0; i < 5; i++) {
				if (shpaeTemp[i] == 0) {
					shape[i] = "spades";
					card[i] = card[i] + 1;
				} else if (shpaeTemp[i] == 1) {
					shape[i] = "diamonds";
					card[i] = card[i] - 12;
				} else if (shpaeTemp[i] == 2) {
					shape[i] = "hearts";
					card[i] = card[i] - 25;
				} else if (shpaeTemp[i] == 3) {
					shape[i] = "clubs";
					card[i] = card[i] - 38;
				} else
					shape[i] = "error";
			}

			// 1->A, 11->JACK, 12->QUEEN, 13->KING
			for (i = 0; i < 5; i++) {
				if (numTemp[i] == 0)
					strNumber[i] = "ace";
				else if (numTemp[i] > 0 && numTemp[i] < 10)
					strNumber[i] = "" + (numTemp[i] + 1);
				else if (numTemp[i] == 10)
					strNumber[i] = "jack";
				else if (numTemp[i] == 11)
					strNumber[i] = "queen";
				else if (numTemp[i] == 12)
					strNumber[i] = "king";
				else
					strNumber[i] = "error";
			}

			// 카드정보 전체오름차순정렬(버블)
			int temp;
			String temp_s;
			String temp_n;
			for (i = 0; i < 4; i++) {
				for (j = i; j < 4; j++) {
					if (card[i] > card[j + 1]) {
						temp = card[i];
						card[i] = card[j + 1];
						card[j + 1] = temp;

						temp_s = shape[i];
						shape[i] = shape[j + 1];
						shape[j + 1] = temp_s;

						temp_n = strNumber[i];
						strNumber[i] = strNumber[j + 1];
						strNumber[j + 1] = temp_n;
					}
				}
			}

			// 카드출력

			for (i = 0; i < 5; i++)
				System.out.print("     " + shape[i] + "." + strNumber[i]);

			System.out.println("");

			System.out.print("     R e s u l t : ");

			// 페어카운트
			for (i = 0; i < 4; i++) {
				for (j = i; j < 4; j++) {
					if (card[i] == card[j + 1]) {
						count++;
					}
				}
			}

			// 플러시카운트
			if (shape[0] == shape[1] && shape[0] == shape[2] && shape[0] == shape[3] && shape[0] == shape[4])
				flush++;

			// 스트레이트카운트
			if (card[0] + 1 == card[1] && card[1] + 1 == card[2] && card[2] + 1 == card[3] && card[3] + 1 == card[4])
				straight++;

			if (card[0] == 1 && card[1] == 10 && card[2] == 11 && card[3] == 12 && card[4] == 13 && flush == 1)
			// 로얄 스트레이트 플러쉬
			{
				System.out.println("Royal Straight Flush");
				System.out.println("");
				gameResult[0]++;
			} else if (flush == 1 && straight == 1) // 스트레이트 플러쉬
			{
				System.out.println("Straight Flush");
				System.out.println("");
				gameResult[1]++;
			} else if (card[0] == card[3] || card[1] == card[4]) // 포카드
			{
				System.out.println("FourCard");
				System.out.println("");
				gameResult[2]++;
			} else if (count == 4) // 풀하우스
			{
				System.out.println("Full House");
				System.out.println("");
				gameResult[3]++;
			} else if (flush == 1) // 플러쉬
			{
				System.out.println("Flush");
				System.out.println("");
				gameResult[4]++;
			} else if (straight == 1) // 스트레이트
			{
				System.out.println("Straight");
				System.out.println("");
				gameResult[5]++;
			} else if (count == 3) // 트리플
			{
				System.out.println("Triple");
				System.out.println("");
				gameResult[6]++;
			} else if (count == 2) // 투 페어
			{
				System.out.println("Two Pair");
				System.out.println("");
				gameResult[7]++;
			} else if (count == 1) // 원 페어
			{
				System.out.println("One Pair");
				System.out.println("");
				gameResult[8]++;
			} else {
				System.out.println("No Pair");
				System.out.println("");
				gameResult[9]++;
			}

			gameCnt++;

			// 안드로이드 이미지인텐트용
			String[] name = new String[5];
			for (i = 0; i < 5; i++) {
				name[i] = String.format("c%s_of_%s", strNumber[i], shape[i]);
				System.out.println(name[i]);
			}
			System.out.println();

		} while (!(gameCnt == 1000));
		System.out.println("게임 실행 횟수: " + gameCnt);
		System.out.println("족보\t\t\t출현 횟수\t확률");
		System.out.println("No Pair:\t\t" + gameResult[9] + "\t\t"
				+ String.format("%.2f", (float) gameResult[9] / gameCnt * 100) + "%");
		System.out.println("One Pair:\t\t" + gameResult[8] + "\t\t"
				+ String.format("%.2f", (float) gameResult[8] / gameCnt * 100) + "%");
		System.out.println("Two Pair:\t\t" + gameResult[7] + "\t\t"
				+ String.format("%.2f", (float) gameResult[7] / gameCnt * 100) + "%");
		System.out.println("Triple:\t\t\t" + gameResult[6] + "\t\t"
				+ String.format("%.2f", (float) gameResult[6] / gameCnt * 100) + "%");
		System.out.println("Straight:\t\t" + gameResult[5] + "\t\t"
				+ String.format("%.2f", (float) gameResult[5] / gameCnt * 100) + "%");
		System.out.println("Flush:\t\t\t" + gameResult[4] + "\t\t"
				+ String.format("%.2f", (float) gameResult[4] / gameCnt * 100) + "%");
		System.out.println("Full House:\t\t" + gameResult[3] + "\t\t"
				+ String.format("%.2f", (float) gameResult[3] / gameCnt * 100) + "%");
		System.out.println("FourCard:\t\t" + gameResult[2] + "\t\t"
				+ String.format("%.2f", (float) gameResult[2] / gameCnt * 100) + "%");
		System.out.println("Straight Flush:\t\t" + gameResult[1] + "\t\t"
				+ String.format("%.2f", (float) gameResult[1] / gameCnt * 100) + "%");
		System.out.println("Royal Straight Flush:\t" + gameResult[0] + "\t\t"
				+ String.format("%.2f", (float) gameResult[0] / gameCnt * 100) + "%");

	}
}
