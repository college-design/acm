/**
 * // pg-range's length is 4/5. also like:pg-range="[1,100,2,50]"
 * <ul pg pg-range="[1,100,2,50,2]" pg-top="Top" pg-pre="Previous" pg-next="Next">
 * <li ><a href="/web/#{pg-index}/#{pg-pagesize}">#{pg-index}</a></li>
 * </ul>
 * 
 * @author {xwzhou}
 */
(function() {
	$("ul[pg]").each(function() {
		var pagination = $(this);
		var pagination_ul_li_previous = pagination.find("li");
		var pg_range = eval(pagination.attr("pg-range"));
		if (pg_range != undefined && pg_range.length >= 4) {
			var pg_start = pg_range[0];
			var pg_end = pg_range[1];
			var pg_current = parseInt(pg_range[2]);
			var pg_pagesize = pg_range[3];
			if (pg_range.length >= 5) {
				var pg_limit = parseInt(pg_range[4]);
				if (pg_limit < 4) {
					pg_limit = 4
				}
				if (pg_current - pg_start > pg_limit) {
					pg_start = pg_current - pg_limit;
				}
				if (pg_end - pg_current > pg_limit) {
					pg_end = pg_current + pg_limit;
				}
			}
			for (var i = pg_start; i <= pg_end; i++) {
				var pagination_ul_li = pagination_ul_li_previous
						.clone();
				var pagination_ul_li_a = pagination_ul_li
						.find("a");
				var href = pagination_ul_li_a.attr("href");
				if (href != null) {
					href = href.replace("#{pg-index}", i)
							.replace("#{pg-pagesize}",
									pg_pagesize);
					pagination_ul_li_a.attr("href", href);
				}
				var pagination_ul_li_a_html = pagination_ul_li_a
						.html();
				if (pagination_ul_li_a_html != null) {
					pagination_ul_li_a_html = pagination_ul_li_a_html
							.replace("#{pg-index}", i);
					pagination_ul_li_a
							.html(pagination_ul_li_a_html);
				}
				if (pg_current == i) {
					pagination_ul_li.addClass("active");
					pagination_ul_li_a.removeAttr("href");
				}
				pagination_ul_li.appendTo(pagination);
			}
			;
			// Next
			var pg_next = pagination.attr("pg-next");
			if (pg_next != undefined) {
				var pagination_ul_li_next = pagination_ul_li_previous
						.clone();
				var pagination_ul_li_next_a = pagination_ul_li_next
						.find("a");
				if (pg_current >= pg_end) {
					pagination_ul_li_next.addClass("active");
					pagination_ul_li_next_a.removeAttr("href");
				} else {
					var href = pagination_ul_li_next_a
							.attr("href");
					if (href != null) {
						href = href.replace("#{pg-index}",
								parseInt(pg_current) + 1)
								.replace("#{pg-pagesize}",
										pg_pagesize);
						pagination_ul_li_next_a.attr("href",
								href);
					}
				}
				var pagination_ul_li_next_a_html = pagination_ul_li_next_a
						.html();
				if (pagination_ul_li_next_a_html != null) {
					if (pg_next == null || pg_next == "") {
						pg_next = "Next";
					}
					pagination_ul_li_next_a_html = pagination_ul_li_next_a_html
							.replace("#{pg-index}", pg_next);
					pagination_ul_li_next_a
							.html(pagination_ul_li_next_a_html);
				}
				pagination_ul_li_next.appendTo(pagination);
			}
			// Top
			var pg_top = pagination.attr("pg-top");
			if (pg_top != undefined) {
				var pagination_ul_li_top = pagination_ul_li_previous
						.clone();
				var pagination_ul_li_top_a = pagination_ul_li_top
						.find("a");
				if (pg_current == pg_start) {
					pagination_ul_li_previous
							.addClass("active");
					pagination_ul_li_top_a.removeAttr("href");
				} else {
					var href = pagination_ul_li_top_a
							.attr("href");
					if (href != null) {
						href = href.replace("#{pg-index}", 1)
								.replace("#{pg-pagesize}",
										pg_pagesize);
						pagination_ul_li_top_a.attr("href",
								href);
					}
				}
				var pagination_ul_li_top_a_html = pagination_ul_li_top_a
						.html();
				if (pagination_ul_li_top_a_html != null) {
					var pg_top = pagination.attr("pg-top");
					if (pg_top == null || pg_top == "") {
						pg_top = "Top";
					}
					pagination_ul_li_top_a_html = pagination_ul_li_top_a_html
							.replace("#{pg-index}", pg_top);
					pagination_ul_li_top_a
							.html(pagination_ul_li_top_a_html);
				}
				pagination_ul_li_top.prependTo(pagination);
			}
			// Previous
			var pg_pre = pagination.attr("pg-pre");
			if (pg_pre != undefined) {
				var pagination_ul_li_previous_a = pagination_ul_li_previous
						.find("a");
				if (pg_current <= pg_start) {
					pagination_ul_li_previous
							.addClass("active");
					pagination_ul_li_previous_a
							.removeAttr("href");
				} else {
					var href = pagination_ul_li_previous_a
							.attr("href");
					if (href != null) {
						href = href.replace("#{pg-index}",
								parseInt(pg_current) - 1)
								.replace("#{pg-pagesize}",
										pg_pagesize);
						pagination_ul_li_previous_a.attr(
								"href", href);
					}
				}
				var pagination_ul_li_previous_a_html = pagination_ul_li_previous_a
						.html();
				if (pagination_ul_li_previous_a_html != null) {
					if (pg_pre == null || pg_pre == "") {
						pg_pre = "Previous";
					}
					pagination_ul_li_previous_a_html = pagination_ul_li_previous_a_html
							.replace("#{pg-index}", pg_pre);
					pagination_ul_li_previous_a
							.html(pagination_ul_li_previous_a_html);
				}
			} else {
				pagination_ul_li_previous.remove();
			}
		}
	});
})();
