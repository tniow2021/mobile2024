package com.example.FootBall.football_minjae

import com.example.FootBall.R

class MainTeamList {

    private val mainTeamList = listOf(
        Team(1, "강원FC", "GwangjuFC", "강원특별자치도", "강릉종합운동장", "강원특별자치도 강릉시 종합운동장길 69", "나르샤", "https://youtu.be/cVupOGWY6Wg?si=atfEsuVQRjiaDOWc", "K리그 1", R.drawable.team01),
        Team(2, "김천 상무 프로축구단", "SangjuSangmu", "경상북도 김천시", "김천종합운동장", "경상북도 김천시 운동장길 1 (삼락동)", "수사불패", "https://youtu.be/RH3UmiCfSSc?si=L3HVhpZrI08szUDB", "K리그 1", R.drawable.team02),
        Team(3, "울산 HD FC", "UlsanHyundaiFC", "울산 광역시", "울산문수월드컵경기장", "울산광역시 남구 문수로 44 (옥동)", "울티메이트", "https://youtu.be/uffLROaITBs?si=qMzwi31s9UuViIJY", "K리그 1", R.drawable.team03),
        Team(4, "포항 스틸러스", "PohangSteelers", "경상북도 포항시", "포항스틸야드", "경상북도 포항시 남구 동해안로6213번길 20 (괴동동)", "마린스", "https://youtu.be/eZk8V3gYzS4?si=gpmBAvisPxcKPrhQ", "K리그 1", R.drawable.team04),
        Team(5, "광주FC", "GwangjuFC", "광주광역시", "광주축구전용구장", "광주광역시 서구 금화로 278 (풍암동)", "빛고을", "https://youtu.be/AjgbGCns-M0?si=lWbDtAWaN36OK_P1", "K리그 1", R.drawable.team05),
        Team(6, "전북 현대 모터스", "JeonbukHyundaiMotors", "전북특별자치도", "전주월드컵경기장", "전북특별자치도 전주시 덕진구 기린대로 1055 (장동)", "매드 그린 보이즈", "https://youtu.be/oqafOE1MEzA?si=H7wZ3J1iuVlLHWW7", "K리그 1", R.drawable.team06),
        Team(7, "인천 유나이티드", "IncheonUnitedFC", "인천광역시", "인천축구전용경기장", "인천광역시 중구 참외전로 246 (도원동)", "파랑검정", "https://youtu.be/Kq1n9Qukp3c?si=nYPA5_kv0gEi0Gl0", "K리그 1", R.drawable.team07),
        Team(8, "대구FC", "DaeguFC", "대구 광역시", "DGB대구은행파크", "대구광역시 북구 고성로 191 (고성동3가)", "그라지예", "https://youtu.be/i_HRNHPquu0?si=lMZyOHIfA2ONaeJC", "K리그 1", R.drawable.team08),
        Team(9, "FC서울", "FcSeoul", "서울 특별시", "서울월드컵경기장", "서울특별시 마포구 월드컵로 240 (성산동)", "수호신", "https://youtu.be/Zx2xzdnCyiQ?si=CMAMVY1OKCbukwXC", "K리그 1", R.drawable.team09),
        Team(10, "대전 하나 시티즌", "DaejeonHanaCitizen", "대전광역시", "대전월드컵경기장", "대전 유성구 월드컵대로 32", "대전 러버스", "https://youtu.be/7AO302zdyKU?si=L6a2d6yYyaxXGF5w", "K리그 1", R.drawable.team10),
        Team(11, "제주 유나이티드", "JejuUnitedFC", "제주특별자치도", "제주월드컵경기장", "제주 서귀포시 월드컵로 33 제주월드컵경기장", "울트라스, 귤케이노, 인세인, 풍백", "https://youtu.be/lNpbNFjggU8?si=BzJTnR0N2uyU3E2U", "K리그 1", R.drawable.team11),
        Team(12, "수원FC", "SuwonFC", "경기도 수원시", "수원종합운동장", "경기도 수원시 장안구 경수대로 893 수원종합운동장 내 수원FC (조원동 775번지)", "리얼크루", "https://youtu.be/rnSv6JmXo00?si=ys80es21Vg7lzFl1", "K리그 1", R.drawable.team12),

        Team(13, "부산 아이파크", "BusanIPark", "부산광역시", "부산아시아드주경기장", "부산광역시 연제구 월드컵대로 344 (거제동)", "P.O.P", "https://youtu.be/qVRQgsEhzp4?si=1kXqQxakiPVJjcci", "K리그 2", R.drawable.team13),
        Team(14, "김포FC", "GimpoCitizen", "경기도 김포시", "김포솔터축구장", "경기도 김포시 김포한강3로 385 (마산동 642-1)", "골든 크루", "https://youtu.be/QJDw43R56sc?si=Ai05ML4lPd976MKN", "K리그 2", R.drawable.team14),
        Team(15, "경남FC", "GyeongnamFC", "경상남도", "창원축구센터", "경상남도 창원시 성산구 비음로 97 (사파정동)", "Aa:V(아브)", "https://youtu.be/osSOEwT2uuA?si=3_s13Ob6AA_Jgm5x", "K리그 2", R.drawable.team15),
        Team(16, "부천FC 1995", "BucheonFC1995", "경기도 부천시", "부천종합운동장", "경기도 부천시 원미구 소사로 482 (춘의동)", "헤르메스", "https://youtu.be/jv776tdUbCM?si=tbuoPUF2gBU2wvnA", "K리그 2", R.drawable.team16),
        Team(17, "FC안양", "Anyang", "경기도 안양시", "안양종합운동장", "경기도 안양시 동안구 평촌대로 389 (비산동)", "A.S.U. RED", "https://youtu.be/d-R1C7-hOLo?si=e8xoSo9Zw-x1sSAo", "K리그 2", R.drawable.team17),
        Team(18, "전남 드래곤즈", "JeonnamDragons", "전라남도", "광양축구전용구장", "전라남도 광양시 백운로 1641 (금호동)", "미르", "https://youtu.be/40FpGGGfk5w?si=8bghyxDv2uYS4xBW", "K리그 2", R.drawable.team18),
        Team(19, "충북 청주FC", "ChungbukCheongju", "충청북도 청주시", "청주종합경기장", "충청북도 청주시 서원구 흥덕로 55 (사직동)", "울트라스NNN", "https://youtu.be/nwqSR9MJuGw?si=Hfg8t4D3uDLIpoUC", "K리그 2", R.drawable.team19),
        Team(20, "성남FC", "SeongnamFC", "경기도 성남시", "탄천종합운동장 주경기장", "경기도 성남시 분당구 탄천로 215 (야탑동)", "블랙리스트", "https://youtu.be/xpZi3fqfeuA?si=_LLeZUxR3fo4SAhC", "K리그 2", R.drawable.team20),
        Team(21, "충남아산 프로축구단", "ChungnamAsan", "충청남도 아산시", "이순신종합운동장", "충청남도 아산시 남부로 370-24 (풍기동)", "아르마다", "https://youtu.be/qnPSP3SvNmM?si=jlmKBhWJpusehNn8", "K리그 2", R.drawable.team21),
        Team(22, "서울 이랜드 FC", "SeoulELand", "서울 특별시", "목동종합운동장", "서울특별시 양천구 안양천로 939 (목동)", "군청", "https://www.youtube.com/watch?v=Yne_VS2A0Fw&list=PLvcYRUVO3MQoRL_kwirSzmaD26DSqUu0V", "K리그 2", R.drawable.team22),
        Team(23, "수원 삼성 블루윙즈", "SuwonSamsungBluewings", "경기도 수원시", "수원월드컵경기장", "경기도 수원시 팔달구 월드컵로 310 (우만동)", "프렌테트리콜로", "https://youtu.be/UZlFDRSy2g0?si=p6REZ48ZrStvmjtI", "K리그 2", R.drawable.team23),
        Team(24, "안산 그리너스 축구단", "AnsanGreeners", "경기도 안산시", "안산와-스타디움", "경기도 안산시 단원구 화랑로 260 (초지동)", "베르도르", "https://youtu.be/Gg56F4_fJ28?si=qXJkGdRPOP5UYrdA", "K리그 2", R.drawable.team24),
        Team(25, "천안시티FC", "CheonanCity", "충청남도 천안시", "천안종합운동장", "충청남도 천안시 서북구 번영로 208 (백석동)", "제피로스", "https://youtu.be/bejLsrW3U6E?si=Wtbo5GrwaDwON0aY", "K리그 2", R.drawable.team25)

    )

    // Getter 함수 추가
    fun getMainTeamList(): List<Team> {
        return mainTeamList
    }
}