from itertools import product, combinations, combinations_with_replacement, permutations

data = "kris"
# data = [5,1,9,5,5,2,10,6,9,9,22,]
window_size = len(data)

val = permutations(data, window_size)
print(len(list(val)))

val = combinations(data, window_size)
print(len(list(val)))

val = combinations_with_replacement(data, window_size)
print(len(list(val)))

# for size in range(len(data)):
#     val = permutations(data, size)
#     print(len(list(val)))
