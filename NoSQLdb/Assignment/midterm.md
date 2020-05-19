# 1번

# 2번
- 참고
 [예제로 배워보는 상황 별 MongoDB 위치 기반 쿼리](https://blog.ull.im/engineering/2019/03/06/mongodb-geospatial-queries.html)
### (1) 지리정보 데이터베이스로 구축
### (2) 원하는 지리 정보 질의어를 5개 이상 정의하여 실행할 것
#### $geoIntersects
- polygon: 목5동
```
db.yangcheongu.find({loc:{$geoIntersects:{$geometry:{type:"Polygon", coordinates:[[[126.869819, 37.531421], [126.888423, 37.531815], [126.883330, 37.543149], [126.869819, 37.531421]]]}}}})
```
  - 실행결과 
    ![image](https://user-images.githubusercontent.com/50271884/82366856-da121980-9a4d-11ea-8367-cdbe6c4f0c0b.png)  

#### $geoWithin
- polygon: 목4동
```
db.yangcheongu.find({loc:{$geoWithin:{$geometry:{type:"Polygon", coordinates:[[[126.863624, 37.539241], [126.864189, 37.529813], [126.867386, 37.529540], [126.872809, 37.537299], [126.867712, 37.539788], [126.863624, 37.539241]]]}}}})
```
  - 실행결과
    ![image](https://user-images.githubusercontent.com/50271884/82366615-712aa180-9a4d-11ea-850d-36af0b618dc9.png)  

- 목동주경기장,목동실내아이스링크 질의
```
db.yangcheongu.find({loc:{$geoWithin:{$geometry:{type:"Polygon", coordinates:[[[126.877101, 37.531373], [126.876715, 37.529527], [126.884772, 37.529816], [126.884515, 37.531560], [126.877101, 37.531373]]]}}}})
```
  - 실행결과  
    ![image](https://user-images.githubusercontent.com/50271884/82366439-23ae3480-9a4d-11ea-9a59-76816a5014ed.png)  
 
#### $centerSphere
- 양천우체국에서
```
db.yangcheongu.find({loc:{$geoWithin:{$centerSphere:[[126.876116, 37.532821], 1000]}}})
```

#### $nearSphere
- 양천우체국에서 6km 이내의 지리정보들을 가까운 순서대로 찾아라
```
db.yangcheongu.find({loc:{$nearSphere:{$geometry: {type: "Point", coordinates: [126.876116, 37.532821]}, $minDistance:100, $maxDistance: 600 }}})
```
  - 실행결과
    ![image](https://user-images.githubusercontent.com/50271884/82368508-8523d280-9a50-11ea-9559-b3190fae065f.png)  
# 3번
