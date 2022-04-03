def cal(info,lion):
    p_score = 0
    l_score = 0
    for i in range(len(info)):
        if info[i] == lion[i] == 0:
            continue
        
        diff = info[i]-lion[i]
        if diff >= 0:
            p_score += 10 - i
        else:
            l_score += 10 - i
    return p_score,l_score

def BT(idx,cnt,lion,info):
    global max_diff
    global answer
    if cnt < 0:
        return 
    if idx > 10:
        p_score,l_score = cal(info,lion)
        diff = l_score - p_score
        if max_diff < diff:
            answer = [num for num in lion]
            max_diff = diff
            answer[10] += cnt
        return

    lion[10-idx] = info[10-idx]+1
    BT(idx+1, cnt-lion[10-idx],lion,info)
    lion[10-idx] = 0
    BT(idx+1, cnt,lion,info)
    
def solution(n, info):
    global max_diff
    global answer
    
    answer = [-1]
    lion = [0]*11
    max_diff = 0
    
    BT(0,n,lion,info)
    return answer