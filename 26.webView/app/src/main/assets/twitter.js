/**
 * 
 */
/*
 * reviews.html#reviewsPage --> twitter.html#twitterPage
 */
$(document).on('pagecreate','#reviewsPage',function(){
	/*
	 * page(#reviewsPage) 생성시 해당페이지의 
	 * 이벤트처리,서버에데이타요청(ajax),UI초기화
	 */
	/*
	 * #reviewsPage 버튼이벤트처리
	 */
	$('#twitterBtn').click(function(e){
		
		//1.loading bar show
		$.mobile.loading('show');
		
		//2.페이지  load --> change
		$.mobile.pageContainer.pagecontainer('load','twitter.html');
		//$.mobile.pageContainer.pagecontainer('change','twitter.html');
		/*
		 * load : 요청후 DOM Tree에 삽입 
		 */
		//$('body').pagecontainer();
		
		e.preventDefault();
	});
});


$(document).on('pagebeforecreate','#twitterPage',function(){
	//alert('#twitterPage--> pagebeforecreate');
	
	$.mobile.loading('show');
	
	//1.ajax요청
	var lat="37.499597";
	var lng="127.031372";
	var dataUrl=null;
	
	if(navigator.geolocation){
		navigator.geolocation.getCurrentPosition(
				function(pos){
					lat = pos.coords.latitude;
					lng = pos.coords.longitude;
					
				}, 
				function(){
					lat="37.499597";
					lng="127.031372";
					alert('위치를사용할수없습니다.(디폴트위치를사용합니다)');
				}, 
				{
					enableHighAccuray:true,
					maximumAge:0
				});
	}else{
		lat="37.499597";
		lng="127.031372";
	}
	dataUrl="https://api.foursquare.com/v2/venues/explore?ll="
        +lat+","+lng+"&radius=500&limit=50&oauth_token=45HKGUJ5WESVY2X1GD0BNDOGWABCPCG2GUH5BWBR2NUXZG5L&v=20170101";
	
	$.ajax({
		async:true,
		url:dataUrl,
		dataType:'jsonp',
		success:function(jsonObject){
			$.mobile.pageContainer.pagecontainer('change','#twitterPage');
			var items = jsonObject.response.groups[0].items;
			var html="";
			$.each(items,function(i,item){
				var $htmlTemplate=$("<li>"
									+"<a href='#'>"
									+"<img class='ui-li-icon profile'/>"
									+"<h3 class='from'></h3>"
									+"<p class='tweet'></p>"
									+"</a>"
									+"</li>");
				$htmlTemplate.find('.profile').attr('src',item.venue.categories[0].icon.prefix+'bg_32.png');
				$htmlTemplate.find('.from').append(item.venue.name);
				$htmlTemplate.find('.tweet').append(item.venue.location.address);
				html+=$htmlTemplate.html();
				$('#tweet-list').append(html);
				$('#tweet-list').listview('refresh',true);
				//$('#tweet-list').enhanceWithin();
			});
			$.mobile.pageContainer.enhanceWithin();
			$.mobile.loading('hide');
		},
		fail:function(){
			alert('ajax request fail!!!');
			$.mobile.loading('hide');
		}
		
	});
	
	
});





