package springboot.study.springbootstudysocket;

import com.alibaba.fastjson.JSON;
import com.sun.deploy.util.ArrayUtil;
import org.junit.Test;
import org.springframework.util.StringUtils;
import springboot.study.springbootstudysocket.domain.Character;
import springboot.study.springbootstudysocket.domain.Message;
import springboot.study.springbootstudysocket.domain.MessageDetail;
import springboot.study.springbootstudysocket.domain.Sentence;
import springboot.study.springbootstudysocket.util.KrcText;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpringbootStudySocketApplicationTests {


	private String strinClass = "\uFEFF[id:$018DEEB9]\n" +
			"[ar:华语群星]\n" +
			"[ti:明天会更好 (六十位歌手大合唱)]\n" +
			"[by:]\n" +
			"[hash:]\n" +
			"[al:]\n" +
			"[sign:]\n" +
			"[qq:]\n" +
			"[total:0]\n" +
			"[offset:232]\n" +
			"[language:eyJjb250ZW50IjpbXSwidmVyc2lvbiI6MX0=]\n" +
			"[1965,1228]<0,543,0>明<543,36,0>天<579,36,0>会<615,37,0>更<652,36,0>好 <688,36,0>(<724,37,0>六<761,35,0>十<796,36,0>位<832,35,0>歌<867,37,0>手<904,36,0>大<940,36,0>合<976,36,0>唱<1012,36,0>) <1048,36,0>- <1084,36,0>华<1120,36,0>语<1156,36,0>群<1192,36,0>星\n" +
			"[3213,1048]<0,36,0>词<36,37,0>：<73,36,0>罗<109,36,0>大<145,36,0>佑<181,36,0>/<217,36,0>张<253,37,0>大<290,36,0>春<326,36,0>/<362,35,0>许<397,36,0>乃<433,36,0>胜<469,37,0>/<506,36,0>李<542,36,0>寿<578,37,0>全<615,35,0>/<650,36,0>邱<686,36,0>复<722,36,0>生<758,36,0>/<794,36,0>张<830,37,0>艾<867,36,0>嘉<903,36,0>/<939,36,0>詹<975,36,0>宏<1011,37,0>志\n" +
			"[4290,179]<0,35,0>曲<35,36,0>：<71,37,0>罗<108,35,0>大<143,36,0>佑\n" +
			"[4474,383]<0,37,0>编<37,36,0>曲<73,36,0>：<109,36,0>陈<145,36,0>志<181,202,0>远\n" +
			"[26410,3003]<0,200,0>轻<200,160,0>轻<360,426,0>敲<786,384,0>醒<1170,330,0>沉<1500,323,0>睡<1823,210,0>的<2033,340,0>心<2373,630,0>灵\n" +
			"[29623,2911]<0,215,0>慢<215,203,0>慢<418,364,0>张<782,405,0>开<1187,250,0>你<1437,184,0>的<1621,318,0>眼<1939,972,0>睛\n" +
			"[32864,1833]<0,219,0>看<219,177,0>看<396,304,0>忙<700,216,0>碌<916,163,0>的<1079,307,0>世<1386,447,0>界\n" +
			"[34905,4582]<0,185,0>是<185,163,0>否<348,93,0>依<441,1125,0>然<1566,265,0>孤<1831,251,0>独<2082,193,0>的<2275,415,0>转<2690,224,0>个<2914,344,0>不<3258,1324,0>停\n" +
			"[39821,2698]<0,223,0>春<223,823,0>风<1046,260,0>不<1306,203,0>解<1509,552,0>风<2061,637,0>情\n" +
			"[42976,2498]<0,339,0>吹<339,334,0>动<673,379,0>少<1052,463,0>年<1515,176,0>的<1691,807,0>心\n" +
			"[46050,2763]<0,225,0>让<225,484,0>昨<709,328,0>日<1037,325,0>脸<1362,285,0>上<1647,206,0>的<1853,344,0>泪<2197,566,0>痕\n" +
			"[49061,4609]<0,381,0>随<381,366,0>记<747,361,0>忆<1108,519,0>风<1627,646,0>干<2273,2336,0>了\n" +
			"[54057,2649]<0,183,0>抬<183,132,0>头<315,215,0>寻<530,445,0>找<975,353,0>天<1328,282,0>空<1610,171,0>的<1781,314,0>翅<2095,554,0>膀\n" +
			"[56944,3358]<0,127,0>候<127,313,0>鸟<440,165,0>出<605,628,0>现<1233,372,0>它<1605,174,0>的<1779,293,0>影<2072,1286,0>迹\n" +
			"[60470,1681]<0,161,0>带<161,137,0>来<298,220,0>远<518,327,0>处<845,157,0>的<1002,336,0>饥<1338,343,0>荒\n" +
			"[62158,4582]<0,222,0>无<222,413,0>情<635,224,0>的<859,370,0>战<1229,458,0>火<1687,188,0>依<1875,216,0>然<2091,186,0>存<2277,465,0>在<2742,187,0>的<2929,298,0>消<3227,1355,0>息\n" +
			"[67164,2905]<0,336,0>玉<336,783,0>山<1119,246,0>白<1365,350,0>雪<1715,500,0>飘<2215,690,0>零\n" +
			"[70308,2780]<0,390,0>燃<390,453,0>烧<843,295,0>少<1138,290,0>年<1428,256,0>的<1684,1096,0>心\n" +
			"[73388,2593]<0,166,0>使<166,317,0>真<483,445,0>情<928,269,0>溶<1197,425,0>化<1622,206,0>成<1828,329,0>音<2157,436,0>符\n" +
			"[76338,3705]<0,317,0>倾<317,866,0>诉<1183,148,0>遥<1331,206,0>远<1537,150,0>的<1687,332,0>祝<2019,1686,0>福\n" +
			"[81546,1601]<0,220,0>唱<220,243,0>出<463,216,0>你<679,184,0>的<863,399,0>热<1262,339,0>情\n" +
			"[83153,1611]<0,281,0>伸<281,400,0>出<681,166,0>你<847,365,0>双<1212,399,0>手\n" +
			"[84769,2837]<0,200,0>让<200,214,0>我<414,182,0>拥<596,559,0>抱<1155,211,0>着<1366,178,0>你<1544,169,0>的<1713,1124,0>梦\n" +
			"[87970,5772]<0,463,0>让<463,280,0>我<743,441,0>拥<1184,404,0>有<1588,175,0>你<1763,257,0>真<2020,434,0>心<2454,226,0>的<2680,914,0>面<3594,2178,0>孔\n" +
			"[94539,1532]<0,197,0>让<197,176,0>我<373,168,0>们<541,153,0>的<694,441,0>笑<1135,397,0>容\n" +
			"[96077,4219]<0,408,0>充<408,195,0>满<603,189,0>着<792,408,0>青<1200,237,0>春<1437,175,0>的<1612,1517,0>骄<3129,1090,0>傲\n" +
			"[100811,5313]<0,219,0>为<219,331,0>明<550,411,0>天<961,292,0>献<1253,449,0>出<1702,447,0>虔<2149,410,0>诚<2559,223,0>的<2782,711,0>祈<3493,1820,0>祷\n" +
			"[107038,2981]<0,215,0>谁<215,154,0>能<369,292,0>不<661,473,0>顾<1134,332,0>自<1466,377,0>己<1843,174,0>的<2017,358,0>家<2375,606,0>园\n" +
			"[110300,2789]<0,194,0>抛<194,173,0>开<367,391,0>记<758,449,0>忆<1207,197,0>中<1404,200,0>的<1604,284,0>童<1888,901,0>年\n" +
			"[113222,3714]<0,737,0>谁<737,168,0>能<905,162,0>忍<1067,372,0>心<1439,237,0>看<1676,447,0>那<2123,366,0>昨<2489,348,0>日<2837,257,0>的<3094,319,0>忧<3413,301,0>愁\n" +
			"[117103,3278]<0,195,0>带<195,187,0>走<382,181,0>我<563,407,0>们<970,218,0>的<1188,841,0>笑<2029,1249,0>容\n" +
			"[120497,2627]<0,154,0>青<154,758,0>春<912,394,0>不<1306,243,0>解<1549,422,0>红<1971,656,0>尘\n" +
			"[123300,2745]<0,659,0>胭<659,362,0>脂<1021,268,0>沾<1289,403,0>染<1692,192,0>了<1884,861,0>灰\n" +
			"[126470,2853]<0,275,0>让<275,547,0>久<822,196,0>违<1018,383,0>不<1401,301,0>见<1702,236,0>的<1938,357,0>泪<2295,558,0>水\n" +
			"[129615,4240]<0,343,0>滋<343,572,0>润<915,292,0>了<1207,226,0>你<1433,203,0>的<1636,440,0>面<2076,2164,0>容\n" +
			"[134808,1596]<0,319,0>唱<319,184,0>出<503,162,0>你<665,157,0>的<822,375,0>热<1197,399,0>情\n" +
			"[136410,1569]<0,283,0>伸<283,393,0>出<676,180,0>你<856,295,0>双<1151,418,0>手\n" +
			"[137984,2752]<0,209,0>让<209,215,0>我<424,184,0>拥<608,537,0>抱<1145,184,0>着<1329,177,0>你<1506,170,0>的<1676,1076,0>梦\n" +
			"[141216,5537]<0,415,0>让<415,327,0>我<742,336,0>拥<1078,398,0>有<1476,180,0>你<1656,310,0>真<1966,359,0>心<2325,306,0>的<2631,528,0>面<3159,2378,0>孔\n" +
			"[147741,1536]<0,214,0>让<214,163,0>我<377,148,0>们<525,161,0>的<686,421,0>笑<1107,429,0>容\n" +
			"[149283,4444]<0,214,0>充<214,401,0>满<615,208,0>着<823,365,0>青<1188,237,0>春<1425,177,0>的<1602,1487,0>骄<3089,1355,0>傲\n" +
			"[154036,6448]<0,181,0>为<181,240,0>明<421,451,0>天<872,101,0>献<973,559,0>出<1532,481,0>虔<2013,769,0>诚<2782,151,0>的<2933,837,0>祈<3770,2678,0>祷\n" +
			"[163622,2753]<0,190,0>轻<190,135,0>轻<325,325,0>敲<650,336,0>醒<986,347,0>沉<1333,300,0>睡<1633,216,0>的<1849,372,0>心<2221,532,0>灵\n" +
			"[166666,3175]<0,211,0>慢<211,185,0>慢<396,378,0>张<774,402,0>开<1176,282,0>你<1458,187,0>的<1645,290,0>眼<1935,1240,0>睛\n" +
			"[170046,1784]<0,188,0>看<188,150,0>看<338,249,0>忙<587,353,0>碌<940,146,0>的<1086,329,0>世<1415,369,0>界\n" +
			"[171837,4994]<0,424,0>是<424,217,0>否<641,423,0>依<1064,638,0>然<1702,361,0>孤<2063,321,0>独<2384,267,0>的<2651,267,0>转<2918,203,0>个<3121,659,0>不<3780,1214,0>停\n" +
			"[176901,2700]<0,159,0>日<159,887,0>出<1046,182,0>唤<1228,190,0>醒<1418,390,0>清<1808,892,0>晨\n" +
			"[179885,2992]<0,401,0>大<401,488,0>地<889,190,0>光<1079,368,0>彩<1447,503,0>重<1950,1042,0>生\n" +
			"[183066,2723]<0,163,0>让<163,461,0>和<624,249,0>风<873,353,0>拂<1226,340,0>出<1566,185,0>的<1751,317,0>音<2068,655,0>响\n" +
			"[186050,3809]<0,345,0>谱<345,636,0>成<981,245,0>生<1226,240,0>命<1466,208,0>的<1674,379,0>乐<2053,1756,0>章\n" +
			"[191351,1404]<0,230,0>唱<230,170,0>出<400,146,0>你<546,129,0>的<675,346,0>热<1021,383,0>情\n" +
			"[192761,1598]<0,96,0>伸<96,502,0>出<598,254,0>你<852,336,0>双<1188,410,0>手\n" +
			"[194364,2800]<0,183,0>让<183,197,0>我<380,213,0>拥<593,563,0>抱<1156,186,0>着<1342,190,0>你<1532,176,0>的<1708,1092,0>梦\n" +
			"[197666,5863]<0,355,0>让<355,258,0>我<613,332,0>拥<945,374,0>有<1319,268,0>你<1587,381,0>真<1968,382,0>心<2350,172,0>的<2522,589,0>面<3111,2752,0>孔\n" +
			"[204229,1460]<0,175,0>让<175,260,0>我<435,141,0>们<576,136,0>的<712,335,0>笑<1047,413,0>容\n" +
			"[205695,4359]<0,211,0>充<211,388,0>满<599,209,0>着<808,380,0>青<1188,202,0>春<1390,187,0>的<1577,1555,0>骄<3132,1227,0>傲\n" +
			"[210231,6050]<0,179,0>让<179,497,0>我<676,441,0>们<1117,186,0>期<1303,642,0>待<1945,333,0>明<2278,419,0>天<2697,174,0>会<2871,631,0>更<3502,2548,0>好\n" +
			"[217097,1479]<0,174,0>唱<174,155,0>出<329,166,0>你<495,195,0>的<690,384,0>热<1074,405,0>情\n" +
			"[218586,1592]<0,79,0>伸<79,505,0>出<584,170,0>你<754,386,0>双<1140,452,0>手\n" +
			"[220183,2946]<0,190,0>让<190,199,0>我<389,198,0>拥<587,506,0>抱<1093,213,0>着<1306,181,0>你<1487,181,0>的<1668,1278,0>梦\n" +
			"[223580,5719]<0,184,0>让<184,421,0>我<605,375,0>拥<980,385,0>有<1365,235,0>你<1600,250,0>真<1850,405,0>心<2255,180,0>的<2435,599,0>面<3034,2685,0>孔\n" +
			"[229970,1449]<0,185,0>让<185,159,0>我<344,157,0>们<501,174,0>的<675,436,0>笑<1111,338,0>容\n" +
			"[231565,4249]<0,142,0>充<142,428,0>满<570,198,0>着<768,336,0>青<1104,272,0>春<1376,154,0>的<1530,1557,0>骄<3087,1162,0>傲\n" +
			"[236277,5733]<0,112,0>让<112,348,0>我<460,399,0>们<859,168,0>期<1027,693,0>待<1720,312,0>明<2032,413,0>天<2445,175,0>会<2620,660,0>更<3280,2453,0>好\n" +
			"[242952,1440]<0,294,0>唱<294,144,0>出<438,138,0>你<576,127,0>的<703,378,0>热<1081,359,0>情\n" +
			"[244398,1593]<0,99,0>伸<99,555,0>出<654,164,0>你<818,362,0>双<1180,413,0>手\n" +
			"[245996,2010]<0,90,0>让<90,297,0>我<387,66,0>拥<453,683,0>抱<1136,176,0>着<1312,188,0>你<1500,244,0>的<1744,266,0>梦\n" +
			"[248014,1416]<0,153,0>拥<153,147,0>抱<300,557,0>着<857,171,0>你<1028,120,0>的<1148,268,0>梦\n" +
			"[249436,3466]<0,147,0>让<147,382,0>我<529,296,0>拥<825,499,0>有<1324,169,0>你<1493,297,0>真<1790,384,0>心<2174,233,0>的<2407,590,0>面<2997,469,0>孔\n" +
			"[252912,3406]<0,71,0>让<71,241,0>我<312,385,0>拥<697,424,0>有<1121,177,0>你<1298,1110,0>真<2408,272,0>心<2680,153,0>的<2833,414,0>面<3247,159,0>孔\n" +
			"[256328,984]<0,147,0>让<147,123,0>我<270,138,0>们<408,171,0>的<579,157,0>笑<736,248,0>容\n" +
			"[257318,3645]<0,244,0>充<244,348,0>满<592,225,0>着<817,362,0>青<1179,249,0>春<1428,154,0>的<1582,1622,0>骄<3204,441,0>傲\n" +
			"[260971,1340]<0,55,0>青<55,674,0>春<729,164,0>的<893,292,0>骄<1185,155,0>傲\n" +
			"[262316,4540]<0,104,0>让<104,178,0>我<282,361,0>们<643,276,0>期<919,471,0>待<1390,456,0>明<1846,393,0>天<2239,274,0>会<2513,663,0>更<3176,1364,0>好\n" +
			"[268804,1423]<0,165,0>唱<165,129,0>出<294,144,0>你<438,148,0>的<586,409,0>热<995,428,0>情\n" +
			"[270233,1584]<0,97,0>伸<97,495,0>出<592,212,0>你<804,374,0>双<1178,406,0>手\n" +
			"[271822,2941]<0,188,0>让<188,262,0>我<450,163,0>拥<613,474,0>抱<1087,215,0>着<1302,194,0>你<1496,170,0>的<1666,1275,0>梦\n" +
			"[275289,3099]<0,186,0>让<186,392,0>我<578,396,0>拥<974,276,0>有<1250,165,0>你<1415,395,0>真<1810,306,0>心<2116,180,0>的<2296,608,0>面<2904,195,0>孔\n" +
			"[278398,2653]<0,136,0>你<136,133,0>真<269,379,0>心<648,291,0>的<939,484,0>面<1423,1230,0>孔\n" +
			"[281699,1219]<0,168,0>让<168,137,0>我<305,146,0>们<451,157,0>的<608,366,0>笑<974,245,0>容\n" +
			"[283098,2304]<0,161,0>充<161,430,0>满<591,195,0>着<786,383,0>青<1169,194,0>春<1363,193,0>的<1556,588,0>骄<2144,160,0>傲\n" +
			"[285410,1963]<0,143,0>青<143,121,0>春<264,152,0>的<416,529,0>骄<945,1018,0>傲\n" +
			"[287785,5776]<0,95,0>让<95,404,0>我<499,422,0>们<921,297,0>期<1218,508,0>待<1726,422,0>明<2148,396,0>天<2544,183,0>会<2727,761,0>更<3488,2288,0>好\n" +
			"[294538,1437]<0,170,0>唱<170,143,0>出<313,151,0>你<464,179,0>的<643,412,0>热<1055,382,0>情\n" +
			"[295981,1586]<0,77,0>伸<77,488,0>出<565,194,0>你<759,399,0>双<1158,428,0>手\n" +
			"[297572,2048]<0,64,0>让<64,310,0>我<374,200,0>拥<574,481,0>抱<1055,194,0>着<1249,187,0>你<1436,194,0>的<1630,418,0>梦\n" +
			"[299628,1445]<0,155,0>拥<155,149,0>抱<304,519,0>着<823,88,0>你<911,153,0>的<1064,381,0>梦\n" +
			"[301079,3274]<0,153,0>让<153,194,0>我<347,355,0>拥<702,456,0>有<1158,174,0>你<1332,379,0>真<1711,344,0>心<2055,237,0>的<2292,641,0>面<2933,341,0>孔\n" +
			"[304363,1052]<0,171,0>真<171,313,0>心<484,189,0>的<673,218,0>面<891,161,0>孔\n" +
			"[305420,2418]<0,161,0>拥<161,136,0>有<297,139,0>你<436,983,0>真<1419,285,0>心<1704,386,0>的<2090,191,0>面<2281,137,0>孔\n" +
			"[307846,1003]<0,137,0>让<137,134,0>我<271,119,0>们<390,139,0>的<529,167,0>笑<696,307,0>容\n" +
			"[308855,4325]<0,111,0>充<111,484,0>满<595,183,0>着<778,387,0>青<1165,181,0>春<1346,226,0>的<1572,1463,0>骄<3035,1290,0>傲\n" +
			"[313487,4238]<0,177,0>让<177,459,0>我<636,346,0>们<982,335,0>期<1317,490,0>待<1807,450,0>明<2257,410,0>天<2667,226,0>会<2893,606,0>更<3499,739,0>好\n";
	@Test
	public void contextLoads() {
	}


	@Test
	public void test7() throws Exception{


		String inputStr = "abcabcab283c";
		String patternStr = "[1-9]{3}";
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(inputStr);
		if(matcher.find()){

			System.out.println(matcher.start());//this will give you index
		}
	}

	@Test
	public void test1() throws Exception{
		String str = "[222,222]";
		String reg = "^\\[\\d*,\\d*\\]$";
		boolean matches = str.matches(reg);
		System.out.println(matches);
	}
	@Test
	public void test2() throws Exception{
		String s = "[76338,3705]<0,317,0>倾<317,866,0>诉<1183,148,0>遥<1331,206,0>远<1537,150,0>的<1687,332,0>祝<2019,1686,0>福";
		String s1 = "倾<317,866,0>诉";

		String[] a = s1.split("<\\d*,\\d*,\\d*>");
		System.out.println(ArrayUtil.arrayToString(a));
	}
	@Test
	public void test3() throws Exception{
		String s1 = "倾<317,866,0>诉";
		String[] a = s1.split("<");
		System.out.println(ArrayUtil.arrayToString(a));
	}

	@Test
	public void test4() throws Exception{
		String s = "0,317,0>倾<317,866,0>诉<1183,148,0>遥<1331,206,0>远<1537,150,0>的<1687,332,0>祝<2019,1686,0";
		String[] a = s.split(">\\D<");
		for (int i = 0; i < a.length; i++) {
			String charTimeStr = a[i];
			String[] charTimeArr = charTimeStr.split(",");

			System.out.println("第" + i + "个字开始时间" + charTimeArr[0]);
			System.out.println("第" + i + "个字持续时间" + charTimeArr[1]);
		}
		System.out.println(ArrayUtil.arrayToString(a));
	}

	@Test
	public void test6() throws Exception{
		String patternStr = "\\[\\d*,\\d*\\]";
		Pattern compile = Pattern.compile(patternStr);
		Matcher matcher = compile.matcher(strinClass);
		if (!matcher.find()) {
			return;
		}

		String myStr = strinClass.substring(matcher.start());

		String[] lineArr = myStr.split("\n") ;
		List<Sentence> sentenceList = new ArrayList<>(lineArr.length);
		List<Message> messageList = new ArrayList<>(lineArr.length);

		for (int i = 0; i <lineArr.length; i++) {
			String curLineStr = lineArr[i];
			Sentence sentence = new Sentence();
			Matcher curLineMatcher = compile.matcher(curLineStr);

			if (!curLineMatcher.find()) {
				return;
			}
			String[] sentenceTimeArr = curLineStr.substring(1, curLineMatcher.end() - 1).split(",");
			sentence.setO(Long.valueOf(sentenceTimeArr[0]));
			sentence.setD(Long.valueOf(sentenceTimeArr[1]));

			String allCharacterStr = curLineStr.substring(curLineMatcher.end());
			String[] allCharacterArr = allCharacterStr.replaceAll("(<\\d*,\\d*,\\d*>\\D)", "$1/").split("/");

			List<Character> characterList = new ArrayList<>();

			for (int j = 0; j < allCharacterArr.length; j++) {
				String characterStr = allCharacterArr[j];
				if (StringUtils.isEmpty(characterStr)) {
					continue;
				}
				Character character = new Character();
				String[] characterTimeArr = characterStr.replaceAll("<", "").replaceAll(">\\D", "").split(",");
				String characterContent = "";
				characterContent = characterStr.substring(characterStr.length() - 1);
				character.setW(">".equals(characterContent) ? "" : characterContent);
				character.setO(Long.valueOf(StringUtils.trimAllWhitespace(characterTimeArr[0])));
				character.setD(Long.valueOf(StringUtils.trimAllWhitespace(characterTimeArr[1])));

				characterList.add(character);
			}
			sentence.setWs(characterList);
			sentenceList.add(sentence);
		}

		for (Sentence sentence : sentenceList) {
			Message message = new Message();
			message.setStart(sentence.getO());
			message.setPersistent(sentence.getD());

			List<Character> characterList = sentence.getWs();
			List<MessageDetail> messageDetailList = parseToMessageDetailList(characterList, 4);
			message.setMessageDetailList(messageDetailList);

			messageList.add(message);
		}
		System.out.println("dhfdfhdshfkdshfsdf");
		System.out.println("dhfdfhdshfkdshfsdf");
		System.out.println("dhfdfhdshfkdshfsdf");

		System.out.println(JSON.toJSONString(messageList));

	}


	private List<MessageDetail> parseToMessageDetailList(List<Character> characterList, int cptNum) {
		List<Character> leftCharacterList = new ArrayList<>(characterList);

		int charsLen = characterList.size();

		int perNum = 1;
		int leftNum = 0;

		if (charsLen > cptNum) {
			perNum = charsLen / cptNum;
			leftNum = charsLen % cptNum;
		}

		List<MessageDetail> messageDetailList = new ArrayList<>();

		for (int i = 0; i < cptNum; i++) {
			MessageDetail messageDetail = new MessageDetail();
			messageDetail.setScreenId(i);
			List<Character> screenCharacterList = new ArrayList<>();

			if (leftCharacterList.size() > 0) {
				for (int j = 0; j < perNum; j++) {
					screenCharacterList.add(characterList.get(i + j));
					leftCharacterList.remove(characterList.get(i + j));
				}
			}

			if (leftNum > 0) {
				screenCharacterList.addAll(leftCharacterList);
			}

			messageDetail.setCharacterList(screenCharacterList);

			messageDetailList.add(messageDetail);
		}
		return messageDetailList;
	}


	@Test
	public void test8() throws Exception{

		String filenm = "D:\\test\\springboot-ws-chatroom\\will_better.krc";//krc文件的全路径加文件名
		String krcText = new KrcText().getKrcText(filenm);

		String patternStr = "\\[\\d*,\\d*\\]";
		Pattern compile = Pattern.compile(patternStr);
		Matcher matcher = compile.matcher(krcText);
		if (!matcher.find()) {
			return;
		}

		String myStr = krcText.substring(matcher.start());

		String[] lineArr = myStr.split("\n") ;
		List<Sentence> sentenceList = new ArrayList<>(lineArr.length);
		List<Message> messageList = new ArrayList<>(lineArr.length);

		for (int i = 0; i <lineArr.length; i++) {
			String curLineStr = lineArr[i];
			Sentence sentence = new Sentence();
			Matcher curLineMatcher = compile.matcher(curLineStr);

			if (!curLineMatcher.find()) {
				return;
			}
			String[] sentenceTimeArr = curLineStr.substring(1, curLineMatcher.end() - 1).split(",");


			long fixOffset = Long.valueOf(sentenceTimeArr[0]).longValue() - 27555;
			if (fixOffset < 0) {
				continue;
			}
			sentence.setO(Long.valueOf(sentenceTimeArr[0]).longValue() - 27555);
			sentence.setD(Long.valueOf(sentenceTimeArr[1]));

			String allCharacterStr = curLineStr.substring(curLineMatcher.end());
			String[] allCharacterArr = allCharacterStr.replaceAll("(<\\d*,\\d*,\\d*>\\D)", "$1/").split("/");

			List<Character> characterList = new ArrayList<>();

			for (int j = 0; j < allCharacterArr.length; j++) {
				String characterStr = allCharacterArr[j];
				if (StringUtils.isEmpty(characterStr) || "\r".equals(characterStr)) {
					continue;
				}
				Character character = new Character();
				String[] characterTimeArr = characterStr.replaceAll("<", "").replaceAll(">\\D", "").split(",");
				String characterContent = "";
				characterContent = characterStr.substring(characterStr.length() - 1);
				character.setW(">".equals(characterContent) ? "" : characterContent);
				character.setO(Long.valueOf(StringUtils.trimAllWhitespace(characterTimeArr[0])));
				character.setD(Long.valueOf(StringUtils.trimAllWhitespace(characterTimeArr[1])));

				characterList.add(character);
			}
			sentence.setWs(characterList);
			sentenceList.add(sentence);
		}
		System.out.println(JSON.toJSONString(sentenceList));



	}















}
