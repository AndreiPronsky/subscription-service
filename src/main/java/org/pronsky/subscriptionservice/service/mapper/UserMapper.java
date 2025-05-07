package org.pronsky.subscriptionservice.service.mapper;

import org.mapstruct.Mapper;
import org.pronsky.subscriptionservice.data.entity.User;
import org.pronsky.subscriptionservice.service.dto.request.CreateUserRequestDto;
import org.pronsky.subscriptionservice.service.dto.request.UpdateUserRequestDto;
import org.pronsky.subscriptionservice.service.dto.response.UserResponseDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto userToUserResponseDto(User user);

    User updateUserRequestDtoToUser(UpdateUserRequestDto updateUserRequestDto);

    User createUserRequestDtoToUser(CreateUserRequestDto updateUserRequestDto);

}
