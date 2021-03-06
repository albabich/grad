[![Codacy Badge](https://app.codacy.com/project/badge/Grade/57c8ebf9cddc4ccf8dd458df2f6bd545)](https://www.codacy.com/gh/albabich/grad/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=albabich/grad&amp;utm_campaign=Badge_Grade)
[![Build Status](https://app.travis-ci.com/albabich/grad.svg?branch=master)](https://travis-ci.com/albabich/grad)

### curl samples (application deployed at application context `grad`).

> For windows use `Git Bash`
>
> #### get All Restaurants
> 'curl -s http://localhost:8080/grad/rest/admin/restaurants --user admin@gmail.com:admin'
>
> #### get Restaurants 100005
> 'curl -s http://localhost:8080/grad/rest/admin/restaurants/100005 --user admin@gmail.com:admin'
>
> > #### get Restaurants not found
> 'curl -s http://localhost:8080/grad/rest/admin/restaurants/10 --user admin@gmail.com:admin'
> 
> #### delete Restaurants 100004
> 'curl -s -X DELETE http://localhost:8080/grad/rest/admin/restaurants/100004 --user admin@gmail.com:admin'
>
> #### create Restaurants
> 'curl -s -X POST http://localhost:8080/grad/rest/admin/restaurants  --user admin@gmail.com:admin -H 'Content-Type: application/json' -d '{"name":"O'\''Hooligans"}''
>
> #### update Restaurants 100005
> 'curl -s -X PUT http://localhost:8080/grad/rest/admin/restaurants/100005  --user admin@gmail.com:admin -H 'Content-Type: application/json' -d '{"name":"MunhellUpdated"}''
> 
> #### get Restaurants with MenuItems today
> 'curl -s http://localhost:8080/grad/rest/restaurants/with-menu/today --user user1@mail.ru:password1'
> 
> #### get MenuItem 100008 for Restaurant 100004
> 'curl -s http://localhost:8080/grad/rest/admin/restaurants/100004/menu-items/100008 --user admin@gmail.com:admin'
> 
>  #### get MenuItem not found for Restaurant 100004
> 'curl -s http://localhost:8080/grad/rest/admin/restaurants/100004/menu-items/10 --user admin@gmail.com:admin'
> 
> > #### create MenuItems for Restaurant 100004
> 'curl -s -X POST http://localhost:8080/grad/rest/admin/restaurants/100004/menu-items  --user admin@gmail.com:admin -H 'Content-Type: application/json' -d '{"name":"idaho potatoes","price":28000}''
> 
> #### update MenuItems 100009 for Restaurant 100004
> 'curl -s -X PUT http://localhost:8080/grad/rest/admin/restaurants/100004/menu-items/100009  --user admin@gmail.com:admin -H 'Content-Type: application/json' -d '{"name":"lobio","price":21000}''
> 
> #### delete MenuItems 100009 for Restaurant 100004
> 'curl -s -X DELETE http://localhost:8080/grad/rest/admin/restaurants/100004/menu-items/100009  --user admin@gmail.com:admin'
> 
> #### create Votes
> 'curl -s -X POST http://localhost:8080/grad/rest/profile/votes/for?restaurantId=100004 --user user1@mail.ru:password1'
> 
>  #### create Votes for Restaurant not found
> 'curl -s -X POST http://localhost:8080/grad/rest/profile/votes/for?restaurantId=10 --user user1@mail.ru:password1'
> 
> #### get Votes today
> 'curl -s http://localhost:8080/grad/rest/votes/today --user user1@mail.ru:password1'