scores = input('5개의 성적을 입력하세요(각 값은 공백으로 분리): ').split()

new_scores = [int(score) for score in scores]
new_scores.sort() #숫자 정렬
print(new_scores)
