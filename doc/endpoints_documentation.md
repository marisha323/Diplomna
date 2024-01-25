
1. AccessStatusController

//основний маппінг  /access

1) /all-status - всі статуси   ;  GetMapping
2) /{id}       - показати статус по ід  ;  приймає: access_statusId ; GetMapping

2. CommentController

//основний маппінг  /comment
1) /add-comment  - додавання коментарія  ; приймає: authorizationHeader, text, video_id;  PostMapping
2) /all        - всі коментарі    ;  приймає: videoId;  GetMapping


3. HomeController

//  основний маппінг   /home  
1) /sort   - сотрування відео по категоріям  ; примає:  videoCategoryId ; GetMapping
2) /search  - пошук відео по назві  ; приймає: title;   GetMapping 


4. PlayListController

//  основний маппінг -   /playlist  

1) /create-playlist   - створення плейліста   ; приймає: title - назва плейліста, accessStatus - доступ (private =1, public = 2)    ;PostMapping
2) /user-playlists    - всі плейлісти юзера   ; приймає : authorizationHeader;  GetMapping

5. PlayListVideoController

// основний маппінг /list_video
1)  /add-video-playlist    - додавання відео в плейліст  ;  приймає: playListId - ід плейліста, videoId - ід відео PostMapping

6. UserController
//  основний маппінг    -   "/user"
1) /update-image   - додавання аватарки юзера  ; приймає: photoUrl - файл картинки   PostMapping
2) /getUserId   - отримання ід юзера із токена. Автоматично витягує із токена всі дані юзера, дешифруючи токен; GetMapping

7. VideoCategoryController
//  основний маппінг    -   /category
1) /all-category -  всі категорії ;  GetMapping
2)  /{id}   - повертаємо відео по цим категоріям ; приймає - ID відео;  GetMapping

8. VideoController
//  основний маппінг -  /video
1) /all-category  - повертає всі відео певної категорії ; GetMapping
2) /{id}    - повертає відео по ід  ;  приймає - ID відео;   GetMapping
3) /uploadNew  - завантажує відео ;  приймає - description, title, file, catrgoryId, accessStatusId
4) /all  -  повертає всі відео в публічному статусі
5) /byteVideo  - повертає масив байтів відео по його назві. Приймає назву відео(name) ; GetMapping


9. WatchedVideoController
   //  основний маппінг    -  /grade
1) /like - зберігає лайк  - приймає:  grade_id - ід лайка . ІД лайка = 1, ІД дізлайка = 2;  video_id - ІД відео   ;GetMapping
2) /dislike  - зберігає дізлайк  ; - приймає:  grade_id - ід дизлайка  ІД дізлайка = 2;  video_id - ІД відео  ;  GetMapping
3) /countlike - кількість лайків   ;  - приймає:  videoId - ІД відео;  GetMapping
4) /countdislike - кількість дізлайків    ;  - - приймає:  videoId - ІД відео; GetMapping
5) /count_watched - кількість переглядів - повертає кількість переглядів - приймає Long videoId;      @GetMapping

