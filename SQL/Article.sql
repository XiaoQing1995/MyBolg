use MyBlog;

create Table Article (
	article_id int not null primary key identity(1,1),
	article_title nvarchar(200) not null,
	article_content nvarchar(max) not null,
	article_date date not null,
	article_img varbinary(max) not null,
	articleclass_id int not null references ArticleClass(articleclass_id),
	);