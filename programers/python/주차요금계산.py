from collections import deque,defaultdict
import math
def solution(fees, records):
    answer = []
    parking = dict()
    timer = defaultdict(int)
    records = [i.split() for i in records]
    
    for rec in records:
        time,car_num,IO = rec
        if IO == 'IN':
            parking[car_num] = time
        else:
            h1,m1 = map(int,time.split(':'))
            h2,m2 = map(int,parking[car_num].split(':'))
            time_delta = (h1-h2)*60 + m1-m2
            timer[car_num] += time_delta
            del parking[car_num]
    
    if parking:
        for k,v in parking.items():
            h1,m1 = map(int,v.split(':'))
            time_delta = (23-h1)*60 + 59-m1
            timer[k] += time_delta
    
    timer = sorted(timer.items(),key=lambda x: x[0])
    for car_num,time in timer:
        if time-fees[0] <= 0:
            answer.append(fees[1])
        else:
            answer.append(fees[1] + math.ceil((time-fees[0])/fees[2])*fees[3])
    
    return answer