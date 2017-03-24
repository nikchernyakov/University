MAX_NUMBER = 1000

MAX_NUMBER.times do |number|
  flag = true
  last_literal = number % 10
  copy_of_number = number

  while number > 0
    unless (number % last_literal).zero?
      flag = false
      break
    end
    number /= 10
    last_literal %= 10
  end

  puts "#{copy_of_number}" if flag == true
end