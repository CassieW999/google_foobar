import numpy as np
import fractions as f


def getQList(nonterminals):
    lenN = len(nonterminals)
    Qlist = []
    for i in range(0, lenN):
        temp = []
        for j in range(0, lenN):
            temp.append(nonterminals[i][j])
        Qlist.append(temp)
    return Qlist


def getRList(nonterminals, countTerminals, len):
    Rlist = []
    for i in range(0, len - countTerminals):
        temp = []
        for j in range(0, countTerminals):
            temp.append(nonterminals[i][j + len - countTerminals])
        Rlist.append(temp)
    return Rlist

def getRes(res_raw):
    len_raw = len(res_raw)
    numerators = []
    denominators = []
    for i in range(0, len_raw):
        res_raw[i] = res_raw[i].limit_denominator(100)
        denominators.append(res_raw[i].denominator)
    lcm = np.lcm.reduce(denominators)
    for i in range(0, len_raw):
        numerators.append(res_raw[i].numerator * lcm / res_raw[i].denominator)
    numerators.append(lcm)
    return numerators


def standardizeInput(m):
    seq = []
    nonterseq = []
    terseq = []
    for i in range(0, len(m)):
        if not np.any(m[i]):
            terseq.append(i)
        else:
            nonterseq.append(i)

    for i in range(0, len(nonterseq)):
        seq.append(nonterseq[i])
    for i in range(0, len(terseq)):
        seq.append(terseq[i])
    ans = []
    for i in range(0, len(m)):
        temp = []
        for j in range(0, len(m)):
            temp.append(m[i][seq[j]])
        ans.append(temp)

    return ans


def solution(m):
    # 1. find nonterminal states
    lengthM = len(m)
    if lengthM < 2:
        return [1,1]
    m = standardizeInput(m)
    nonterminals = [];
    countTerminals = 0
    for i in range(0, lengthM):
        if not np.any(m[i]):
            countTerminals += 1
            if i == 0:
                ans = []
                ans.append(1)
                for i in range(0, lengthM-2):
                    ans.append(0)
                ans.append(1)
                return ans
        else:
            sum = np.sum(m[i])
            for j in range(0, len(m[i])):
                m[i][j] = f.Fraction(m[i][j], sum)
            nonterminals.append(m[i])
    # 2. get Qmatrix
    QList = getQList(nonterminals)
    Q = np.matrix(QList, dtype=float)
    # 3. get IMatrix
    I = np.diag(np.ones(len(Q)))
    # 4. get RMatrix
    RList = getRList(nonterminals, countTerminals, lengthM)
    R = np.matrix(RList, dtype=float)
    # 5. get FMatrix
    F = (I - Q) ** (-1)
    # 6. get FR
    FR = F * R

    # get result
    res_raw = map(f.Fraction, FR[0].A1.tolist())
    result = getRes(res_raw)
    return result