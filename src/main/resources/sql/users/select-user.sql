-- SELECT
--     u.id id,
--     u.user_name userName,
--     u.pass_word passWord,
--     u.full_name fullName,
-- FROM USERS u
-- WHERE 1 = 1

SELECT
    u.users_id userId,
    u.username userName,
    u.password passWord,
    u.full_name fullName,
    u.email email,
    u.skype skypeName,
    u.phone phone,
    u.level_id levelId,
    u.image_id imageId
FROM USERS u
WHERE 1 = 1
