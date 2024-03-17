use MyBlog;

create Table Article (
	articleId int not null primary key identity(1,1),
	articleTitle nvarchar(200) not null,
	articleSummaryContent nvarchar(200) not null,
	articleContent nvarchar(MAX) not null,
	articleDate date not null,
	articleClassId int not null references ArticleCLass(articleClassId),
	articleOriginalImage varchar(300) not null,
	articleThumbnailImage varchar(300) not null,
	);