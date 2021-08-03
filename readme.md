### curl samples (application deployed at application context `grad`).

> For windows use `Git Bash`
>
> #### get All Restaurants
> 'curl -s http://localhost:8080/grad/rest/admin/restaurants --user admin@gmail.com:admin'
>
> #### get Restaurants 100005
> 'curl -s http://localhost:8080/grad/rest/admin/restaurants/100005 --user admin@gmail.com:admin'
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
> #### get MenuItem 100008 for Restaurant 100004
> 'curl -s http://localhost:8080/grad/rest/admin/restaurants/100004/menu-items/100008 --user admin@gmail.com:admin'
> 