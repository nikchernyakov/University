MAX_NUMBER = 1000

MAX_NUMBER.times do |number|
  flag = true
  number_length = number.to_s.length
  copy_of_number = number

  while number > 0
    unless (number % number_length).zero?
      flag = false
      break
    end
    number /= 10
    number_length -= 1
  end

  puts "#{copy_of_number}" if flag == true
end