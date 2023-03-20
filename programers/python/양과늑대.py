from collections import deque,defaultdict

def BT(q,sheep,wolf):
    global answer,infos,dic
    answer = max(answer,sheep)
    for i in range(len(q)):
        index = q.popleft()
        if infos[index] == 0:
            for i in dic[index]:
                q.append(i)
            BT(q,sheep+1,wolf)
            for i in dic[index]:
                q.pop()
        
        else:
            if sheep>wolf+1:
                for i in dic[index]:
                    q.append(i)
                BT(q,sheep,wolf+1)
                for i in dic[index]:
                    q.pop()
                    
        q.append(index)

def solution(info, edges):
    global infos,dic,answer
    answer = 0
    infos = info
    dic = defaultdict(list)
    q = deque()
    for edge in edges:
        dic[edge[0]].append(edge[1])
    
    q.append(0)
    BT(q,0,0)
    return answer