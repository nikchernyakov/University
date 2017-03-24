numbers = {}

1000.times do |number|
  count_of_sum = 0
  (1..number / 2).each do |new_element|
    sum_of_elements = 0
    current_element = new_element
    while sum_of_elements < number
      sum_of_elements += current_element
      current_element += 1
    end
    count_of_sum += 1 if sum_of_elements == number 
  end
  numbers[number] = count_of_sum if count_of_sum > 5
end

puts numbers.length
numbers.each do |key, value|
  puts "#{key} (#{value})"
end
