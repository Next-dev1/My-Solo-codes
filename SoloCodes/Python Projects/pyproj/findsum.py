def find_sum(n):
    # Base case: if n is 1, the sum is 1
    if n == 1:
        return 1
    # Recursive case: sum of n + sum of first (n-1) numbers
    else:
        return n + find_sum(n - 1)
    
n = int(input("Enter a number: "))

# Calculate the sum 

result = find_sum(n)
print(f"The sum of the first {n} integers is {result}.")
