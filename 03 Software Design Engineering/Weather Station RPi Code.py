# import libraries
import grovepi
import math
import time
from grovepi import *

# Set configuration for LEDs
led_green = 2
led_blue  = 3
led_red   = 4

# Grove Temperature & Humidity Sensor Pro to digital port D5
sensor = 5

# Grove Light Sensor on analog port A0
light_sensor = 0

# Start recording data if the daylight is sensed
threshold = 20  # value for light threshold
grovepi.pinMode(light_sensor, "INPUT")

# temp_humidity_sensor_type
# Grove Base Kit comes with the blue sensor.
blue = 0    # The Blue colored sensor.
white = 1   # The White colored sensor.

# Initialize LEDS for output
pinMode(led_blue, "OUTPUT")
pinMode(led_green, "OUTPUT")
pinMode(led_red, "OUTPUT")

# Initialize variables
counter = 0  # counter for number of readings

time_gap = 1  # time between readings

# Create function to call which lights should illuminate
# Each color will be 0 (off) or 1(on)
def lights(red, blue, green):
    digitalWrite(led_red, red)  # Send low/high to LED
    digitalWrite(led_blue, blue)  # Send low/high to LED
    digitalWrite(led_green, green)  # Send low/high to LED

def display_Menu():
    print("""

        Weather station Menu:

        0. Display Menu Options
        1. Run Self-Test
        2. Take 1 Reading
        3. Take many Readings
        4. Exit\n""")

def self_Test():

  print("\nYou have entered the self test\n")
  print("The lights will come on for 5 seconds and then go back off\n")
  # Lights out for start
  lights(0, 0, 0)

  print("Red light on\n")
  lights(1, 0, 0)
  time.sleep(5)

  print("Red light off and blue light on\n")
  lights(0, 1, 0)
  time.sleep(5)

  print("Blue light off and green light on\n")
  lights(0, 0, 1)
  time.sleep(5)

  print("All lights off\n")
  lights(0, 0, 0)
  print("\tIf any lights were dim or didn't light up, please contact the company\n")
  print("\tPlease make another choice from the menu\n")
  return


def get_Readings(num_Readings, time_gap):

    # Lights out for start
    lights(0, 0, 0)
    counter = 0

    while (True & (counter < num_Readings)):
        try:
            # This example uses the blue colored sensor.
            # The 1st parameter is port, the 2nd parameter is sensor type
            [temp, humidity] = grovepi.dht(sensor, blue)

            # convert temp from C to F
            temp_F = ((9 * temp)/5) + 32

            # Get sensor value
            sensor_value = grovepi.analogRead(light_sensor)

            # check light sensor, record only if above hreshold
            if (sensor_value > threshold):

                #check to see if data is valid (not nan)
                if math.isnan(temp) == False and math.isnan(humidity) == False:

                    # Print output to screen
                    print("\n\tReading # %i" %(counter+1))
                    print("\ttemp = %.02f F humidity = %.02f%%\n"%(temp_F, humidity))

                    # Logic to turn the proper LEDs on
                    if (temp > 60 and temp < 85 and humidity < 80):
                      lights(0, 0, 1)  # Turn on green LED, other LEDs are off

                    elif (temp > 85 and temp < 95 and humidity < 80):
                      lights(0, 1, 0)  # Turn on blue LED, other LEDs are off

                    elif (temp > 95):
                      lights(1, 0, 0)  # Turn on red LED, other LEDs are off

                    elif (humidity > 80):
                      lights(0, 1, 1)  # Turn on blue and green LED, other LED is off

                    else:
                        lights(0, 0, 0)  # lights to remain off

                    time.sleep(time_gap)  # interval between readings
                    counter +=1
            else:
                print("Light is not hitting the sensor, the data is not being recorded")
                time.sleep(10) #check every 10 seconds for light

        # Catch exception for divide by zero if the sensor value drops to zero (no light sensed)
        except ZeroDivisionError:
            print("Zero Reading on Sensor")

        except KeyboardInterrupt:
            lights(0, 0, 0)  #turn lights off

        except IOError:
            print("Error")  # Exception for IOError

    # turn lights off before retturning to menu
    lights(0, 0, 0)  # turn lights off
    return

# Main program
# Display menu to user
display_Menu()

while True:

  selection=input("\tPlease Select a menu option (Use 0 to display menu): ")

  if selection == '0':
      display_Menu()

  elif selection == '1':

    self_Test()

  elif selection == '2':

    get_Readings(1,0)

  elif selection == '3':

    print("\n\tPlease enter the following values")
    num_Readings = int(input("\tPlease Enter number of readings to take: "))
    total_time = int(input("\tPlease enter total time between readings in seconds: "))
    get_Readings(num_Readings, total_time)

  elif selection == '4':

    print("\n\t\tThank you for using our service\n")
    lights(0, 0, 0) #turn lights off

    break

  else:

    print("Unknown Option Selected!")