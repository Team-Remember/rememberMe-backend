package com.yjh.rememberme.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Random;

/**
 * Original Code
 * https://github.com/DaeyeonKim97/leisurethat-backend/blob/main/src/main/java/com/steady/leisurethatapi/auth/service/MailService.java
 */

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;
    @Transactional
    public int sendEmail(String toEmail) throws MessagingException {

        Random random = new Random();
        int certCode = random.nextInt(888888) + 111111;
        System.out.println("인증번호 : " + certCode);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("bulkkotnamja@gmail.com");
        helper.setTo(toEmail);

        helper.setSubject("[레저댓] 회원가입 인증 코드");
        helper.setText("text/html","<!DOCTYPE html>\n" +
                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">\n" +
                "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "  <title></title>\n" +
                "\n" +
                "\n" +
                "</head>\n" +
                "<body style=\"margin:0;padding:0;word-spacing:normal;background-color:#939297;\">\n" +
                "  <div role=\"article\" aria-roledescription=\"email\" lang=\"en\" style=\"text-size-adjust:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;background-color:#939297;\">\n" +
                "    <table role=\"presentation\" style=\"width:100%;border:none;border-spacing:0;\">\n" +
                "      <tr>\n" +
                "        <td align=\"center\" style=\"padding:0;\">\n" +
                "\n" +
                "          <table role=\"presentation\" style=\"width:94%;max-width:600px;border:none;border-spacing:0;text-align:left;font-family:Arial,sans-serif;font-size:16px;line-height:22px;color:#363636;\">\n" +
                "            <tr>\n" +
                "              <td style=\"padding:40px 30px 30px 30px;text-align:center;font-size:24px;font-weight:bold;\">\n" +
                "                <a href=\"https://i.ibb.co/0GmynLx/image.png\"><img src=\"\" alt=\"\" border=\"0\"></a>\n" +
                "            </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td style=\"padding:30px;background-color:#ffffff;\">\n" +
                "                <!--title-->\n" +
                "                <h1 align=\"center\" style=\"margin-top:0;margin-bottom:16px;font-size:26px;line-height:32px;font-weight:bold;letter-spacing:-0.02em;\">REmember Me를 이용해주셔서 감사합니다.</h1>\n" +
                "                <div align=\"center\"><img style=\"height:250px;\" src=\"https://i.ibb.co/0GmynLx/image.png\"></div>\n" +
                "                <!--first paragraph-->\n" +
                "                <p style=\"margin:0;\"></p>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "\n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"padding:35px 30px 11px 30px;font-size:0;background-color:#ffffff;border-bottom:1px solid #f0f0f5;border-color:rgba(201,201,207,.35);\">\n" +
                "\n" +
                "                <!-- <div class=\"col-sml\" style=\"display:inline-block;width:100%;max-width:145px;vertical-align:top;text-align:left;font-family:Arial,sans-serif;font-size:14px;color:#363636;\">\n" +
                "                  <img src=\"\" width=\"115\" alt=\"\" style=\"width:115px;max-width:80%;margin-bottom:20px;\">\n" +
                "                </div> -->\n" +
                "\n" +
                "                <div  class=\"col-lge\" style=\"display:inline-block;width:100%;max-width:395px;vertical-align:top;padding-bottom:20px;font-family:Arial,sans-serif;font-size:16px;line-height:22px;color:#363636;\">\n" +
                "                  <!--second paragraph-->             \n" +
                "                    <p style=\"color:#707070; font-size: 12px; margin-top:0;margin-bottom:12px;\">\n" +
                "                      이메일 인증번호를 입력하시면 인증절차가<br>\n" +
                "                      완료되어 가입을 진행하실 수 있습니다.\n<br>\n" +
                "                      인증시간이 만료되었을 경우,\n\n<br>\n" +
                "                      인증번호 재발송을 진행해 주시기 바랍니다.\n<br>\n" +
                "                    </p>\n" +
                "                 <!--third paragraph-->\n" +
                "                    <p style=\"margin-top:0;margin-bottom:18px;\"></p>\n" +
                "                  <p style=\"margin:0;\"><div href=\"https://example.com/\" style=\"background: #00AEEF; text-decoration: none; padding: 10px 25px; color: #ffffff; border-radius: 4px; display:inline-block; mso-padding-alt:0;text-underline-color:#ff3884\"><!--[if mso]><i style=\"letter-spacing: 25px;mso-font-width:-100%;mso-text-raise:20pt\">&nbsp;</i><![endif]--><span style=\"mso-text-raise:10pt;font-weight:bold;\">"+certCode+"</span><!--[if mso]><i style=\"letter-spacing: 25px;mso-font-width:-100%\">&nbsp;</i><![endif]--></div></p>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "\n" +
                "\n" +
                "            <tr>\n" +
                "              <td style=\"padding:30px;text-align:center;font-size:12px;background-color:#404040;color:#cccccc;\">\n" +
                "                <p style=\"margin:0 0 8px 0;\"><a href=\"\" style=\"text-decoration:none;\"><img src=\"https://assets.codepen.io/210284/facebook_1.png\" width=\"40\" height=\"40\" alt=\"f\" style=\"display:inline-block;color:#cccccc;\"></a> <a href=\"\" style=\"text-decoration:none;\"><img src=\"https://assets.codepen.io/210284/twitter_1.png\" width=\"40\" height=\"40\" alt=\"t\" style=\"display:inline-block;color:#cccccc;\"></a></p>\n" +
                "                <p style=\"margin:0;font-size:14px;line-height:20px;\">Copyright 2022. leisurethat all rights reserved.<br></p>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>");
        mailSender.send(message);

        System.out.println("메일 발송 완료");

        return certCode;
    }
    @Transactional
    public void sendEmailForMatchId(String toEmail,String username) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("bulkkotnamja@gmail.com");
        helper.setTo(toEmail);

        helper.setSubject("[레저댓] 아이디 찾기 안내");
        helper.setText("text/html","<!DOCTYPE html>\n" +
                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">\n" +
                "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "  <title></title>\n" +
                "\n" +
                "\n" +
                "</head>\n" +
                "<body style=\"margin:0;padding:0;word-spacing:normal;background-color:#939297;\">\n" +
                "  <div role=\"article\" aria-roledescription=\"email\" lang=\"en\" style=\"text-size-adjust:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;background-color:#939297;\">\n" +
                "    <table role=\"presentation\" style=\"width:100%;border:none;border-spacing:0;\">\n" +
                "      <tr>\n" +
                "        <td align=\"center\" style=\"padding:0;\">\n" +
                "\n" +
                "          <table role=\"presentation\" style=\"width:94%;max-width:600px;border:none;border-spacing:0;text-align:left;font-family:Arial,sans-serif;font-size:16px;line-height:22px;color:#363636;\">\n" +
                "            <tr>\n" +
                "              <td style=\"padding:40px 30px 30px 30px;text-align:center;font-size:24px;font-weight:bold;\">\n" +
                "                <a href=\"https://ibb.co/YQfBKss\"><img src=\"\" alt=\"\" border=\"0\"></a>\n" +
                "            </td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td style=\"padding:30px;background-color:#ffffff;\">\n" +
                "                <!--title-->\n" +
                "                <h1 align=\"center\" style=\"margin-top:0;margin-bottom:16px;font-size:26px;line-height:32px;font-weight:bold;letter-spacing:-0.02em;\">레저댓을 이용해 주셔서 감사합니다.</h1>\n" +
                "                <div align=\"center\"><img style=\"height:250px;\" src=\"https://i.ibb.co/sRk3XrP/leisurethat.png\"></div>\n" +
                "                <!--first paragraph-->\n" +
                "                <p style=\"margin:0;\"></p>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "\n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"padding:35px 30px 11px 30px;font-size:0;background-color:#ffffff;border-bottom:1px solid #f0f0f5;border-color:rgba(201,201,207,.35);\">\n" +
                "\n" +
                "                <!-- <div class=\"col-sml\" style=\"display:inline-block;width:100%;max-width:145px;vertical-align:top;text-align:left;font-family:Arial,sans-serif;font-size:14px;color:#363636;\">\n" +
                "                  <img src=\"\" width=\"115\" alt=\"\" style=\"width:115px;max-width:80%;margin-bottom:20px;\">\n" +
                "                </div> -->\n" +
                "\n" +
                "                <div  class=\"col-lge\" style=\"display:inline-block;width:100%;max-width:395px;vertical-align:top;padding-bottom:20px;font-family:Arial,sans-serif;font-size:16px;line-height:22px;color:#363636;\">\n" +
                "                  <!--second paragraph-->             \n" +
                "                    <p style=\"color:#707070; font-size: 12px; margin-top:0;margin-bottom:12px;\">\n" +
                "                        이메일 인증번호를 입력하시면 인증절차가<br>                  \n" +
                "                        완료되어 가입을 진행하실 수 있습니다.<br>                     \n" +
                "                        인증시간이 만료되었을 경우,<br>\n" +
                "                        인증번호 재발송을 진행해 주시기 바랍니다.<br>\n" +
                "                    </p>\n" +
                "                 <!--third paragraph-->\n" +
                "                    <p style=\"margin-top:0;margin-bottom:18px;\"></p>\n" +
                "                  <p style=\"margin:0;\"><div href=\"https://example.com/\" style=\"background: #00AEEF; text-decoration: none; padding: 10px 25px; color: #ffffff; border-radius: 4px; display:inline-block; mso-padding-alt:0;text-underline-color:#ff3884\"><!--[if mso]><i style=\"letter-spacing: 25px;mso-font-width:-100%;mso-text-raise:20pt\">&nbsp;</i><![endif]--><span style=\"mso-text-raise:10pt;font-weight:bold;\"> "+ username + "</span><!--[if mso]><i style=\"letter-spacing: 25px;mso-font-width:-100%\">&nbsp;</i><![endif]--></div></p>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "\n" +
                "\n" +
                "            <tr>\n" +
                "              <td style=\"padding:30px;text-align:center;font-size:12px;background-color:#404040;color:#cccccc;\">\n" +
                "                <p style=\"margin:0 0 8px 0;\"><a href=\"\" style=\"text-decoration:none;\"><img src=\"https://assets.codepen.io/210284/facebook_1.png\" width=\"40\" height=\"40\" alt=\"f\" style=\"display:inline-block;color:#cccccc;\"></a> <a href=\"\" style=\"text-decoration:none;\"><img src=\"https://assets.codepen.io/210284/twitter_1.png\" width=\"40\" height=\"40\" alt=\"t\" style=\"display:inline-block;color:#cccccc;\"></a></p>\n" +
                "                <p style=\"margin:0;font-size:14px;line-height:20px;\">Copyright 2022. leisurethat all rights reserved.<br></p>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>");
        mailSender.send(message);

        System.out.println("메일 발송 완료");
    }
    @Transactional
    public void sendEmailForMatchPassword(String toEmail, String tempPassword) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("yeonjaehwe123@gmail.com");
        helper.setTo(toEmail);

        helper.setSubject("[Remember] 임시 비밀번호");
        helper.setText("text/html", "<!DOCTYPE html>\n" +
                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">\n" +
                "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "  <title></title>\n" +
                "\n" +
                "\n" +
                "</head>\n" +
                "<body style=\"margin:0;padding:0;word-spacing:normal;background-color:#939297;\">\n" +
                "  <div role=\"article\" aria-roledescription=\"email\" lang=\"en\" style=\"text-size-adjust:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;background-color:#939297;\">\n" +
                "    <table role=\"presentation\" style=\"width:100%;border:none;border-spacing:0;\">\n" +
                "      <tr>\n" +
                "        <td align=\"center\" style=\"padding:0;\">\n" +
                "\n" +
                "          <table role=\"presentation\" style=\"width:94%;max-width:600px;border:none;border-spacing:0;text-align:left;font-family:Arial,sans-serif;font-size:16px;line-height:22px;color:#363636;\">\n" +
                "            <tr>\n" +
                "              <td align=\"center\" style=\"padding:35px 30px 11px 30px;font-size:0;background-color:#ffffff;border-bottom:1px solid #f0f0f5;border-color:rgba(201,201,207,.35);\">\n" +
                "\n" +
                "                <!-- <div class=\"col-sml\" style=\"display:inline-block;width:100%;max-width:145px;vertical-align:top;text-align:left;font-family:Arial,sans-serif;font-size:14px;color:#363636;\">\n" +
                "                  <img src=\"\" width=\"115\" alt=\"\" style=\"width:115px;max-width:80%;margin-bottom:20px;\">\n" +
                "                </div> -->\n" +
                "\n" +
                "                <div  class=\"col-lge\" style=\"display:inline-block;width:100%;max-width:395px;vertical-align:top;padding-bottom:20px;font-family:Arial,sans-serif;font-size:16px;line-height:22px;color:#363636;\">\n" +
                "                  <!--second paragraph-->             \n" +
                "                    <p style=\"color:#707070; font-size: 12px; margin-top:0;margin-bottom:12px;\">\n" +
                "                        leisurethat을 이용해주셔서 감사합니다.<br>                  \n" +
                "                        회원가입 시 등록한 정보를 수정하려면<br>                     \n" +
                "                        마이페이지-설정에서 변경 하실 수 있습니다.,<br>\n" +
                "                    </p>\n" +
                "                 <!--third paragraph-->\n" +
                "                    <p style=\"margin-top:0;margin-bottom:18px;\"></p>\n" +
                "                  <p style=\"margin:0;\"><div href=\"https://example.com/\" style=\"background: #00AEEF; text-decoration: none; padding: 10px 25px; color: #ffffff; border-radius: 4px; display:inline-block; mso-padding-alt:0;text-underline-color:#ff3884\"><!--[if mso]><i style=\"letter-spacing: 25px;mso-font-width:-100%;mso-text-raise:20pt\">&nbsp;</i><![endif]--><span style=\"mso-text-raise:10pt;font-weight:bold;\"> "+ tempPassword + "</span><!--[if mso]><i style=\"letter-spacing: 25px;mso-font-width:-100%\">&nbsp;</i><![endif]--></div></p>\n" +
                "                </div>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "\n" +
                "\n" +
                "            <tr>\n" +
                "              <td style=\"padding:30px;text-align:center;font-size:12px;background-color:#404040;color:#cccccc;\">\n" +
                "                <p style=\"margin:0 0 8px 0;\"><a href=\"\" style=\"text-decoration:none;\"><img src=\"https://assets.codepen.io/210284/facebook_1.png\" width=\"40\" height=\"40\" alt=\"f\" style=\"display:inline-block;color:#cccccc;\"></a> <a href=\"\" style=\"text-decoration:none;\"><img src=\"https://assets.codepen.io/210284/twitter_1.png\" width=\"40\" height=\"40\" alt=\"t\" style=\"display:inline-block;color:#cccccc;\"></a></p>\n" +
                "                <p style=\"margin:0;font-size:14px;line-height:20px;\">Copyright 2022. leisurethat all rights reserved.<br></p>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>");
        mailSender.send(message);

        System.out.println("메일 발송 완료");

    }

}
