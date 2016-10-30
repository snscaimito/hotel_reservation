#
# Cookbook Name:: hotel
# Recipe:: default
#
# Copyright (c) 2016 The Authors, All Rights Reserved.

group 'hotel_services'

user 'room_srv' do
  group 'hotel_services'
  system true
  shell '/bin/bash'
end

bash 'make services' do
  code <<-EOH
    mkdir -p /var/caimito
    cp -r /vagrant/dist/* /var/caimito
    chmod +x /var/caimito/room-inventory-server.jar
    ln -sf /var/caimito/room-inventory-server.jar /etc/init.d/room-inventory-service
  EOH
end



service 'room-inventory-service' do
  action :start
  start_command "/etc/init.d/room-inventory-service start"
  stop_command "/etc/init.d/room-inventory-service stop"
end
