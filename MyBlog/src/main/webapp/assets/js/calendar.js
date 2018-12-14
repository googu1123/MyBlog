
var d = new Date();

var yy = d.getFullYear();

var mm = d.getMonth()+1;
if( mm < 10 ) mm = '0' + mm;

var dd = d.getDate();

function kCalendar(id, date) {
	var kCalendar = document.getElementById(id);
	
	if( typeof( date ) !== 'undefined' ) {
		date = date.split('-');
		date[1] = date[1] - 1;
		date = new Date(date[0], date[1], date[2]);
	} else {
		var date = new Date();
	}
	var currentYear = date.getFullYear();
	//년도를 구함
	
	var currentMonth = date.getMonth() + 1;
	//연을 구함. 월은 0부터 시작하므로 +1, 12월은 11을 출력
	
	var currentDate = date.getDate();
	//오늘 일자.
	
	date.setDate(1);
	var currentDay = date.getDay();
	//이번달 1일의 요일은 출력. 0은 일요일 6은 토요일
	
	var dateString = new Array('sun', 'mon', 'tue', 'wed', 'thu', 'fri', 'sat');
	var lastDate = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	if( (currentYear % 4 === 0 && currentYear % 100 !== 0) || currentYear % 400 === 0 )
		lastDate[1] = 29;
	//각 달의 마지막 일을 계산, 윤년의 경우 년도가 4의 배수이고 100의 배수가 아닐 때 혹은 400의 배수일 때 2월달이 29일 임.
	
	var currentLastDate = lastDate[currentMonth-1];
	var week = Math.ceil( ( currentDay + currentLastDate ) / 7 );
	//총 몇 주인지 구함.
	
	if(currentMonth != 1)
		var prevDate = currentYear + '-' + ( currentMonth - 1 ) + '-' + currentDate;
	else
		var prevDate = ( currentYear - 1 ) + '-' + 12 + '-' + currentDate;
	//만약 이번달이 1월이라면 1년 전 12월로 출력.
	
	if(currentMonth != 12) 
		var nextDate = currentYear + '-' + ( currentMonth + 1 ) + '-' + currentDate;
	else
		var nextDate = ( currentYear + 1 ) + '-' + 1 + '-' + currentDate;
	//만약 이번달이 12월이라면 1년 후 1월로 출력.

//	if( currentMonth < 10 )
//		var currentMonth = '0' + currentMonth;
	//10월 이하라면 앞에 0을 붙여준다.
	
	var monthNames = ["January", "February", "March", "April", "May", "June",
	                  "July", "August", "September", "October", "November", "December"
	                ];
	
	var calendar = '';
	calendar += '<table>';
	calendar += '<caption>';
	calendar += '<a onclick="kCalendar(\'' +  id + '\', \'' + prevDate + '\')" style="cursor:pointer"> < </a>';
	calendar += '&nbsp;&nbsp;';
//	calendar += '' + currentYear + '년 ' + currentMonth + '월';
	calendar += monthNames[currentMonth-1] +' ' + currentYear;
	calendar += '&nbsp;&nbsp;';
	calendar += '<a onclick="kCalendar(\'' + id + '\', \'' + nextDate + '\')" style="cursor:pointer"> > </a>';
	calendar += '</caption>';
	calendar += '<thead>';
	calendar += '<tr>';
	calendar += '<th scope="col" title="Sunday" style="color:deeppink">S</th>';
	calendar += '<th scope="col" title="Monday">M</th>';
	calendar += '<th scope="col" title="Tuesday">T</th>';
	calendar += '<th scope="col" title="Wednesday">W</th>';
	calendar += '<th scope="col" title="Thursday">T</th>';
	calendar += '<th scope="col" title="Friday">F</th>';
	calendar += '<th scope="col" title="Saturday" style="color:deepskyblue">S</th>';
	calendar += '</tr>';
	calendar += '</thead>';
	calendar += '<tbody>';
	var dateNum = 1 - currentDay;
	
	for(var i = 0; i < week; i++) {
		calendar += '			<tr>';
		for(var j = 0; j < 7; j++, dateNum++) {
			if( dateNum < 1 || dateNum > currentLastDate ) {
				calendar += '				<td class="' + dateString[j] + '"> </td>';
				continue;
			}
			
			if(yy==currentYear && mm==currentMonth && dd==dateNum)
			{
				calendar += '				<td class="today"><a href="#">' + dateNum + '</a></td>';
			}
			else
			{
				calendar += '				<td class="' + dateString[j] + '">' + dateNum + '</td>';
			}
		}
		calendar += '			</tr>';
	}
	
	calendar += '</tbody>';
	calendar += '</table>';
	
	kCalendar.innerHTML = calendar;
}