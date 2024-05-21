-- тут можно разместить данные для тестов
merge into posts (post_id, author, creation_date, description, photo_url)
values (0, 'aleksandrTEST@ya.ru', CURRENT_TIMESTAMP(), 'test', 'https://www.h2database.com/html/images/h2-logo-2.png');

merge into posts (post_id, author, creation_date, description, photo_url)
values (1, 'aleksandr2TEST@ya.ru', CURRENT_TIMESTAMP(), 'test2', 'https://www.h2database.com/html/images/h2-logo-2.png');
